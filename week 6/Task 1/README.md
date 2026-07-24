# Git Hands-On Lab - Task 1

## 📁 Project Structure
```
Task 1/
├── README.md                  # This file - Getting started guide
├── Git-Lab-Commands.md        # Complete command reference
├── run-git-lab.ps1           # Automated PowerShell script
└── GitDemo/                   # Git repository folder
    ├── .git/                  # Git metadata (hidden)
    └── welcome.txt            # Sample file to track
```

## 🚀 Quick Start

### Option 1: Run Automated Script (Recommended)
Open PowerShell in the "Task 1" folder and run:
```powershell
.\run-git-lab.ps1
```

### Option 2: Manual Step-by-Step

1. **Configure Git** (one-time setup)
   ```bash
   git config --global user.name "PratapSakthivel"
   git config --global user.email "pratapssakthivel@gmail.com"
   ```

2. **Navigate to GitDemo folder**
   ```bash
   cd GitDemo
   ```

3. **Check Git status**
   ```bash
   git status
   ```

4. **Add file to staging area**
   ```bash
   git add welcome.txt
   ```

5. **Commit changes**
   ```bash
   git commit -m "Initial commit: Added welcome.txt file"
   ```

6. **View commit history**
   ```bash
   git log
   ```

## 🌐 Connect to Remote Repository (GitLab)

### Step 1: Create GitLab Account & Repository
1. Go to https://gitlab.com
2. Sign up for a free account (don't use Cognizant credentials)
3. Create a new project named "GitDemo"
4. Copy the repository URL (e.g., `https://gitlab.com/PratapSakthivel/GitDemo.git`)

### Step 2: Link Local to Remote
```bash
git remote add origin https://gitlab.com/PratapSakthivel/GitDemo.git
```

### Step 3: Push to Remote
```bash
git push -u origin master
```

Or if your default branch is `main`:
```bash
git push -u origin main
```

## 📝 Lab Objectives Checklist

- [x] Setup machine with Git Configuration
- [x] Create GitDemo project folder
- [x] Initialize Git repository
- [x] Create welcome.txt file
- [x] Add file to staging area
- [x] Commit changes to local repository
- [ ] Create GitLab account
- [ ] Create remote GitDemo repository
- [ ] Push local repository to remote

## 🛠️ Useful Git Commands

| Command | Purpose |
|---------|---------|
| `git init` | Initialize new Git repository |
| `git status` | Check status of working directory |
| `git add <file>` | Stage file for commit |
| `git commit -m "message"` | Commit staged changes |
| `git log` | View commit history |
| `git remote -v` | View remote repositories |
| `git push` | Upload commits to remote |
| `git pull` | Download commits from remote |

## 📚 Reference Files

- **Git-Lab-Commands.md** - Detailed command reference with explanations
- **run-git-lab.ps1** - Automated script to complete local setup

## ⏱️ Estimated Time
30 minutes

## 👤 Student Information
- **Name:** PratapSakthivel
- **Email:** pratapssakthivel@gmail.com

## ✅ Completion Status

Local Setup: ✅ **COMPLETED**
- Git configured
- Repository initialized
- File created and committed

Remote Setup: ⏳ **PENDING**
- Requires GitLab account creation
- Requires manual push to remote

---

**Next Action:** Create your GitLab account and follow the "Connect to Remote Repository" instructions above.
