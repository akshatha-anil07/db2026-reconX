#!/bin/sh
set -eu
SCRIPT_DIR=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)
if [ -x "$SCRIPT_DIR/backend/mvnw" ]; then
  exec "$SCRIPT_DIR/backend/mvnw" "$@"
fi
if [ -f "$SCRIPT_DIR/backend/mvnw.cmd" ]; then
  exec cmd.exe /c "$SCRIPT_DIR/backend/mvnw.cmd" "$@"
fi
printf 'Maven wrapper not found in %s/backend\n' "$SCRIPT_DIR" >&2
exit 1
