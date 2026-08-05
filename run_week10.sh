#!/bin/bash
# Run QuizBattleGUI from the week10 folder
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "$0")" && pwd)"
cd "$ROOT_DIR/week10" || { echo "week10 directory not found"; exit 1; }

echo "Compiling Java files in week10..."
if compgen -G "*.java" > /dev/null; then
  javac *.java
else
  echo "No .java files found in week10"
  exit 1
fi

echo "Launching QuizBattleGUI..."
java QuizBattleGUI
