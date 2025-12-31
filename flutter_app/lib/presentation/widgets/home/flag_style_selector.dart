import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import '../../../data/repositories/settings_repository.dart';

/// Flag style selector widget for home screen.
class FlagStyleSelector extends StatefulWidget {
  const FlagStyleSelector({super.key});

  @override
  State<FlagStyleSelector> createState() => _FlagStyleSelectorState();
}

class _FlagStyleSelectorState extends State<FlagStyleSelector> {
  String _style = 'squared';

  @override
  void initState() {
    super.initState();
    final settingsRepo = context.read<SettingsRepository>();
    _style = settingsRepo.getFlagStyle();
  }

  @override
  Widget build(BuildContext context) {
    return IconButton(
      icon: const Icon(
        Icons.flag,
        color: Colors.white,
        size: 28,
      ),
      onPressed: _selectFlagStyle,
      tooltip: _style == 'waving' ? 'Flag style: Waving' : 'Flag style: Squared',
    );
  }

  Future<void> _selectFlagStyle() async {
    final selectedStyle = await showDialog<String>(
      context: context,
      builder: (dialogContext) => AlertDialog(
        title: const Text('Select Flag Style'),
        content: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            _buildStyleOption(dialogContext, 'Squared', 'squared'),
            _buildStyleOption(dialogContext, 'Waving', 'waving'),
          ],
        ),
      ),
    );

    if (selectedStyle != null && selectedStyle != _style) {
      final settingsRepo = context.read<SettingsRepository>();
      await settingsRepo.setFlagStyle(selectedStyle);
      if (!mounted) return;
      setState(() {
        _style = selectedStyle;
      });
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(
          content: Text(
            selectedStyle == 'waving'
                ? 'Waving flags enabled'
                : 'Squared flags enabled',
          ),
          duration: const Duration(seconds: 1),
        ),
      );
    }
  }

  Widget _buildStyleOption(
    BuildContext context,
    String label,
    String value,
  ) {
    final isSelected = value == _style;
    return ListTile(
      title: Text(label),
      leading: Icon(
        isSelected ? Icons.check_circle : Icons.circle_outlined,
        color: isSelected ? const Color(0xFF399DC5) : Colors.grey,
      ),
      selected: isSelected,
      onTap: () => Navigator.pop(context, value),
    );
  }
}
