# EC2 Docker Setup Course Notes

This document covers the step-by-step process of setting up Docker and Docker Compose on an Amazon Linux 2023 EC2 instance.

---

## 1. Connecting to EC2 Instance

### SSH Command Format
```bash
ssh -i "path/to/private-key" ec2-user@<public-ip-address>
```

### Example from this session:
```bash
ssh -i "%USERPROFILE%\.ssh\key1thriamself" ec2-user@98.92.18.116
```

### Important Notes:
- The private key file must exist in the `.ssh` directory
- On Windows, use `%USERPROFILE%\.ssh\keyname` or full path
- The default user for Amazon Linux is `ec2-user`

---

## 2. Installing Docker on Amazon Linux 2023

### Installation Command
```bash
sudo dnf install docker -y
```

### Common Errors:
❌ **Wrong command (causes error):**
```bash
sudo dnf install docker -ysudo systemctl start docker
# Error: argument -y/--assumeyes: ignored explicit argument 'sudo'
```

✅ **Correct approach - separate commands:**
```bash
sudo dnf install docker -y
```

---

## 3. Starting and Enabling Docker

```bash
sudo systemctl start docker
sudo systemctl enable docker
```

### Explanation:
- `systemctl start docker` - Starts the Docker service immediately
- `systemctl enable docker` - Enables Docker to start on system boot

---

## 4. Installing Docker Compose

### Step 1: Create the CLI plugins directory
```bash
mkdir -p ~/.docker/cli-plugins/
```

### Step 2: Download Docker Compose
```bash
curl -SL https://github.com/docker/compose/releases/latest/download/docker-compose-linux-x86_64 -o ~/.docker/cli-plugins/docker-compose
```

### Step 3: Make it executable
```bash
chmod +x ~/.docker/cli-plugins/docker-compose
```

---

## 5. Docker Permission Issues

### Problem:
```
unable to get image 'nginx:latest': permission denied while trying to connect to the docker API at unix:///var/run/docker.sock
```

### Solution:
```bash
sudo usermod -aG docker ec2-user
newgrp docker
```

### Explanation:
- The `ec2-user` needs permission to access the Docker socket
- `usermod -aG docker ec2-user` adds the user to the docker group
- `newgrp docker` applies the group change without logging out

---

## 6. Docker Compose Basics

### Basic docker-compose.yml Structure
```yaml
services:
  nginx-server-1:
    image: nginx:latest
    ports:
      - "8081:80"
```

### Running Docker Compose
```bash
docker compose up -d
```

### Viewing Running Containers
```bash
docker ps
```

### Stopping Containers
```bash
docker stop <container-id>
```

### Removing Containers
```bash
docker rm <container-id>
```

### Important Notes:
- The `version` attribute in docker-compose.yml is obsolete and will be ignored
- Always stop containers before removing them, or use `docker rm -f` for force removal

---

## 7. Common Docker Commands

| Command | Description |
|---------|-------------|
| `docker ps` | List running containers |
| `docker ps -a` | List all containers |
| `docker stop <id>` | Stop a container |
| `docker rm <id>` | Remove a container |
| `docker rm -f <id>` | Force remove a running container |
| `docker compose up -d` | Start containers in detached mode |
| `docker compose down` | Stop and remove containers |
| `docker system prune` | Clean up unused data |

### Container ID Shortcuts:
- You can use the first few characters of the container ID
- Example: `docker stop d8` instead of `docker stop d8a5a3e5c036`

---

## 8. Troubleshooting YAML Errors

### Error:
```
yaml: line 3, column 9: mapping values are not allowed in this context
```

### Cause:
This usually indicates incorrect YAML syntax, such as:
- Incorrect indentation
- Missing required fields
- Malformed key-value pairs

### Example of Correct YAML:
```yaml
services:
  nginx-server-1:
    image: nginx:latest
    ports:
      - "8081:80"
  nginx-server-2:
    image: nginx:latest
    ports:
      - "8082:80"
```

### Tips:
- Always use spaces (not tabs) for indentation
- Ensure proper nesting of elements
- Validate YAML syntax before running

---

## 9. Quick Reference - Full Setup Process

```bash
# 1. Connect to EC2
ssh -i "key1thriamself" ec2-user@<IP>

# 2. Install Docker
sudo dnf install docker -y

# 3. Start and enable Docker
sudo systemctl start docker
sudo systemctl enable docker

# 4. Install Docker Compose
mkdir -p ~/.docker/cli-plugins/
curl -SL https://github.com/docker/compose/releases/latest/download/docker-compose-linux-x86_64 -o ~/.docker/cli-plugins/docker-compose
chmod +x ~/.docker/cli-plugins/docker-compose

# 5. Fix permissions
sudo usermod -aG docker ec2-user
newgrp docker

# 6. Create docker-compose.yml and run
docker compose up -d

# 7. Check running containers
docker ps
```

---

## 10. Summary

In this session, we successfully:
1. ✅ Connected to an EC2 instance via SSH
2. ✅ Installed Docker on Amazon Linux 2023
3. ✅ Started and enabled Docker service
4. ✅ Installed Docker Compose
5. ✅ Fixed permission issues for Docker access
6. ✅ Deployed multiple nginx containers
7. ✅ Managed containers (stop, remove, recreate)

---

*Generated from hands-on EC2 Docker setup session*

