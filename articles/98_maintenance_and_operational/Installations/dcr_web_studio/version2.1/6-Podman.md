
# Podman Installation 

## Installing Podman

 **Podman**:
    Install Podman using your distribution's package manager. For example, on RHEL-based systems:

   ```
   bash
   
   sudo dnf install -y podman
   ```

   Refer to the official documentation at https://podman.io/getting-started/installation for complete installation steps.

3. **Podman Compose**:
    Install **Podman Compose** to support Compose YAML workflows. Use either:

   - DNF:

     ```
     bash
     
     sudo dnf install -y podman-compose
     ```

   - Or pip:

     ```
     bash
     
     pip3 install --user podman-compose
     ```

> **Note**:  Podman Compose provides native compatibility for Compose YAML files with Podman pods.



## Podman Command Reference for Enterprise Linux Environments

This reference document captures the Podman CLI commands used by DevOps engineers (e.g., "May") when managing Fabric Web Studio environments on Enterprise Linux systems.

---

### 1. Container Management

#### List Containers
- `podman ps`  
  Lists all running containers.
- `podman ps -a`  
  Lists all containers, including stopped ones.

#### Start/Stop Containers
- `podman start <container>`  
  Starts a specific container.
- `podman stop <container>`  
  Stops a specific container.
- `podman stop -a`  
  Stops all containers.
- `podman stop --all`  
  Stops all containers.

#### Remove Containers
- `podman rm <container>`  
  Removes a specific container.
- `podman rm -f <container>`  
  Force removes a container.
- `podman rm -af`  
  Force removes all containers.

#### Inspect and Logs
- `podman logs <container>`  
  Displays logs for a container.
- `podman inspect <container>`  
  Shows low-level information about a container.
- `podman exec -it <container> <cmd>`  
  Executes a command inside a running container interactively.

---

### 2. Image and Registry Management
- `podman images`  
  Lists local container images.
- `podman pull <image>`  
  Pulls an image from a remote registry.
- `podman login -u <user> -p <password> <registry>`  
  Logs in to a container registry.

---

### 3. Pod Management

#### View Pods
- `podman pod ls`  
  Lists all pods.
- `podman pod ps`  
  Lists running pods.

#### Manage Pods
- `podman pod create`  
  Creates a new pod.
- `podman pod rm <pod>`  
  Removes a specified pod.
- `podman pod rm -f <pod>`  
  Force removes a pod.
- `podman pod rm -af`  
  Force removes all pods.

#### Logs and Inspection
- `podman pod logs <pod>`  
  Shows logs from all containers in a pod.
- `podman pod inspect <pod>`  
  Displays detailed info for a pod.

---

### 4. Network Management
- `podman network ls`  
  Lists existing networks.
- `podman network create <name>`  
  Creates a new container network.
- `podman network rm <name>`  
  Removes a specified network.
- `podman network rm -a`  
  Removes all networks.
- `podman network prune`  
  Removes unused networks.

---

### 5. System Utilities
- `podman version`  
  Shows the installed Podman version.
- `podman system reset`  
  Wipes all Podman data (containers, images, volumes).
- `podman system migrate`  
  Migrates container formats to the latest version.
- `podman volume prune`  
  Deletes all unused volumes.

---

### 6. Compose Support
- `podman compose -v`  
  Displays the version of Podman Compose (if installed).

---

### 7. Socket and Service Management
- `systemctl --user enable --now podman.socket`  
  Enables and starts Podman socket for rootless API access.
- `systemctl --user status podman.socket`  
  Checks the status of the Podman socket.
- `loginctl enable-linger <user>`  
  Enables lingering for systemd user services.
