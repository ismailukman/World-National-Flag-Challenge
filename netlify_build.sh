#!/usr/bin/env bash
set -euo pipefail

FLUTTER_DIR="${HOME}/flutter"

if [ ! -d "${FLUTTER_DIR}" ]; then
  git clone --depth 1 --branch stable https://github.com/flutter/flutter.git "${FLUTTER_DIR}"
fi

export PATH="${FLUTTER_DIR}/bin:${PATH}"

flutter --version

pushd flutter_app >/dev/null
flutter pub get
flutter build web --release
popd >/dev/null
