
# Cheat Sheet - Fabric Web Studio – Docker Compose Installation

## Prerequisites
- Obtain a login account for docker.share.cloud.k2view.com
- <a href="/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/6-Docker-Compose.md">Install Docker & Docker Compose plugin</a> (not the deprecated `docker-compose` binary).
- If installing on Windows, use **WSL2** and install under a **Linux distribution** (not under `/mnt/c`).
- Internet access is required to pull images from K2view Nexus unless using offline image loading.

## Package Contents
- `README.html` – Documentation
- `k2space.sh` – Script to create/start/stop spaces
- `.env` – Configures Fabric and Git integration
- `compose.yaml` – Base Compose config
- `studio_*.config` – Studio profiles (e.g., `studio_pg`, `studio_cass`)
- `tls-config.yaml`, `common.config`, `.env-tdmspace`, etc.

## Key Configurations
- **Git Integration** (optional): Define `GIT_REPO`, `GIT_BRANCH`, `GIT_TOKEN`, and `GIT_USERNAME` in `.env`
- **TLS Certificates** (optional): Replace default certs in `ssl-certs/` (`cert.cer`, `cert.key`)

## Important Notes
- **Default credentials**: `admin` / `admin`
- **Persistent data** is stored under `persistent-data/<space-name>`
- **In doubt?** Consult the <a href="/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/Installation.html">installation instructions.</a>

## Installation Steps

### 1. Install Docker and Docker Compose
Use [Docker Desktop](https://www.docker.com/products/docker-desktop) (includes Compose) or install via your package manager.

See: <a href="/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/6-Docker-Compose.md">Docker and Docker Compose Installation</a>

### 2. Set Up Environment
Create a base directory (e.g., `~/K2view` or `/opt/K2view`).  
On Windows (WSL), avoid using `/mnt/c`; use `/home/<user>/K2view`.

```bash
mkdir -p ~/K2view
cd ~/K2view
```

### 3. Download the Studio Package

```bash
# Download and extract to ~/K2view
wget https://nexus.share.cloud.k2view.com/repository/k2view-download/web-studio/Studio-Docker-latest.zip
unzip Studio-Docker-latest.zip
mv Studio-Docker Studio
```

### 4. Configure Git & TLS (Optional)

Edit `~/K2view/Studio/.env` to include:
```env
GIT_REPO=<your_repo>
GIT_BRANCH=main
GIT_TOKEN=<your_token>
GIT_USERNAME=<your_user>
```

TLS: Replace `ssl-certs/cert.cer` and `ssl-certs/cert.key` with your PEM-formatted certs.

### 5. Select a Fabric Web Studio Profile
Available profiles:
- `studio` *(default, SQLite)*
- `studio_pg` *(PostgreSQL)*
- `studio_cass` *(Cassandra)*
- `studio_pg_cass` *(PostgreSQL + Cassandra)*

### 6. Login to K2view Nexus
```bash
docker login -u <your_user> https://docker.share.cloud.k2view.com
```

### 7. Create and Launch a Space
Make script executable:
```bash
cd Studio
chmod 700 k2space.sh
chmod 644 *.config
```

Create a space:
```bash
./k2space.sh create <space_name>
# Optional: Specify profile
./k2space.sh create --profile=studio_pg <space_name>
```

### 8. Access Web Studio
- Local: `http://localhost/<space_name>`
- Remote: `https://<host>/<space_name>`

For remote access, you need to configure TLS. See Step 4.

Login with:
```text
Username: admin
Password: admin
```

## Offline Docker Image Download (If No Internet)
<a href="/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/Installation.html#docker-image-offline-package-download">Offline Package Download Instructions</a>

## Operating
<a href="/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/Operating.md">Operating Instructions.</a>

## Troubleshooting
<a href="/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/Troubleshooting.md">Troubleshooting Instructions.</a>
