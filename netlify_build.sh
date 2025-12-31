#!/usr/bin/env bash
set -euo pipefail

FLUTTER_DIR="${HOME}/flutter"
APP_DIR="flutter_app"

if [ ! -d "${FLUTTER_DIR}" ]; then
  git clone --depth 1 --branch stable https://github.com/flutter/flutter.git "${FLUTTER_DIR}"
fi

export PATH="${FLUTTER_DIR}/bin:${PATH}"

if [ ! -f "${APP_DIR}/pubspec.yaml" ]; then
  echo "Flutter project not found at ${APP_DIR}/pubspec.yaml"
  exit 1
fi

pushd "${APP_DIR}" >/dev/null
flutter --version
flutter pub get
flutter build web --release
popd >/dev/null
