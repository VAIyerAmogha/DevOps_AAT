#!/usr/bin/env bash
set -euo pipefail

echo "[1/5] Updating package index"
sudo apt update

echo "[2/5] Installing required packages (Java, Maven, Make, Docker)"
sudo apt install -y openjdk-17-jdk maven make docker.io

echo "[3/5] Enabling and starting Docker service"
sudo systemctl enable --now docker

echo "[4/5] Adding current user to docker group"
sudo usermod -aG docker "$USER"

echo "[5/5] Setup complete"
echo "Log out and back in (or reboot) so docker group membership takes effect."
echo "After re-login, run: make run"
