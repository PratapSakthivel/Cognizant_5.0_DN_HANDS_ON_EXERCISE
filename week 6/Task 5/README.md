# Git Remote Push & Cleanup - Task 5

## 📁 Project Structure
```
Task 5/
├── README.md                      # This file - Getting started guide
├── QUICK-REFERENCE.md             # Quick cheat sheet
├── Git-Remote-Commands.md         # Complete command reference
├── run-git-remote-lab.ps1         # Interactive PowerShell script
└── GitRemoteDemo/                 # Git repository folder
    ├── .git/                      # Git metadata (hidden)
    └── README.md                  # Initial file
```

## 🎯 Objectives

- Explain how to clean up and push back to remote Git
- Execute steps involving cleanup and remote push
- Understand git pull and git push workflows
- Verify changes in remote repository

## ⚠️ Prerequisites

- **Task 4 completion recommended** (conflict resolution)
- GitLab or GitHub account (free)
- Remote repository created
- Internet connection

## 🚀 Quick Start

### Option 1: Run Interactive Script (Recommended)
Open PowerShell in the "Task 5" folder and run:
```powershell
.\run-git-remote-lab.ps1
```

**Note:** You'll need to provide your GitLab/GitHub repository URL when prompted.

### Option 2: Manual Step-by-Step

#### Part 1: Verify and Cleanup

1. **Verify master is clean**
   ```bash
   cd GitRemoteDemo
   git status
   ```

2. **List all branches**
   ```bash
   git branch -a
   ```

3. **Delete unnecessary local branches**
   ```bash
   git branch -d old-feature
   ```

#### Part 2: Remote Operations

4. **Add remote (if not already added)**
   ```bash
   git remote add origin https://gitlab.com/yourusername/GitRemoteDemo.git
   ```

5. **Verify remote**
   ```bash
   git remote -v
   ```

6. **Pull from remote**
   ```bash
   git pull origin master
   # Or for main branch:
   git pull origin main
   ```

7. **Push to remote**
   ```bash
   git push origin master
   # Or for main branch:
   git push origin main
   ```

8. **Verify in GitLab/GitHub**
   - Open your repository in browser
   - Check commits are visible
   - Verify files are uploaded

## 🌐 Understanding Remote Operations

### What is a Remote?
A remote is a version of your repository hosted on the internet or network. Common remotes:
- **origin**: Default name for primary remote
- **upstream**: Often used for original repository in forks

### Key Commands:

| Command | Purpose |
|---------|---------|
| `git remote -v` | List all remotes |
| `git remote add <name> <url>` | Add new remote |
| `git remote remove <name>` | Remove remote |
| `git pull origin <branch>` | Fetch and merge from remote |
| `git push origin <branch>` | Upload commits to remote |
| `git push -u origin <branch>` | Push and set upstream |
| `git fetch origin` | Download without merging |

## 🔄 Pull vs Fetch

### Git Pull (Fetch + Merge):
```bash
git pull origin master
```
- Downloads changes from remote
- Automatically merges into current branch
- Can cause conflicts if local changes exist

### Git Fetch (Download Only):
```bash
git fetch origin
git merge origin/master
```
- Downloads changes from remote
- Doesn't modify working directory
- Allows you to review before merging

## 📤 Push Workflows

### First Push:
```bash
git push -u origin master
```
- `-u` sets upstream tracking
- Future pushes can just use `git push`

### Regular Push:
```bash
git push
```
- Uploads commits to tracked remote branch

### Force Push (Dangerous!):
```bash
git push --force
```
- Overwrites remote history
- Use only when absolutely necessary
- Can cause data loss for collaborators

## 📋 Lab Tasks Checklist

- [x] Git repository initialized
- [x] Initial files created
- [ ] Verify master is clean
- [ ] List all branches
- [ ] Add remote repository
- [ ] Pull from remote
- [ ] Push changes to remote
- [ ] Verify changes in GitLab/GitHub

## 🔧 Setting Up Remote Repository

### GitLab:
1. Go to https://gitlab.com
2. Click "New project"
3. Enter project name: "GitRemoteDemo"
4. Choose visibility (Public/Private)
5. Click "Create project"
6. Copy repository URL

### GitHub:
1. Go to https://github.com
2. Click "+" → "New repository"
3. Enter repository name: "GitRemoteDemo"
4. Choose visibility (Public/Private)
5. Don't initialize with README
6. Click "Create repository"
7. Copy repository URL

## 🔐 Authentication

### HTTPS (Username/Password or Token):
```bash
git remote add origin https://gitlab.com/username/repo.git
git push -u origin master
# Enter username and password/token when prompted
```

### SSH (Key-based):
```bash
git remote add origin git@gitlab.com:username/repo.git
git push -u origin master
# No password needed if SSH key is set up
```

### Personal Access Token:
- GitHub: Settings → Developer settings → Personal access tokens
- GitLab: Settings → Access Tokens
- Use token instead of password

## ⏱️ Estimated Time
10 minutes

## 👤 Student Information
- **Name:** PratapSakthivel
- **Email:** pratapssakthivel@gmail.com

## ✅ Expected Results

### Before Push:
```bash
$ git remote -v
origin  https://gitlab.com/username/GitRemoteDemo.git (fetch)
origin  https://gitlab.com/username/GitRemoteDemo.git (push)

$ git status
Your branch is ahead of 'origin/master' by 3 commits.
```

### After Push:
```bash
$ git push origin master
Counting objects: 10, done.
Writing objects: 100% (10/10), 1.2 KiB | 0 bytes/s, done.
Total 10 (delta 0), reused 0 (delta 0)
To https://gitlab.com/username/GitRemoteDemo.git
   abc1234..def5678  master -> master

$ git status
Your branch is up to date with 'origin/master'.
nothing to commit, working tree clean
```

## 💡 Best Practices

1. **Pull before push** - Always get latest changes first
2. **Commit locally first** - Don't push uncommitted changes
3. **Write good commit messages** - Help your team understand changes
4. **Push regularly** - Don't let local changes pile up
5. **Review before pushing** - Use `git log` to check commits
6. **Don't force push** - Unless absolutely necessary
7. **Use branches** - Keep master/main stable

## 🔧 Troubleshooting

### Remote already exists:
```bash
git remote remove origin
git remote add origin <new-url>
```

### Push rejected (non-fast-forward):
```bash
git pull origin master
# Resolve any conflicts
git push origin master
```

### Authentication failed:
```bash
# Use personal access token instead of password
# Or set up SSH keys
```

### Branch diverged:
```bash
git pull origin master --rebase
git push origin master
```

## 🌍 Remote Branch Management

### View remote branches:
```bash
git branch -r
```

### View all branches:
```bash
git branch -a
```

### Delete remote branch:
```bash
git push origin --delete branch-name
```

### Prune deleted remote branches:
```bash
git remote prune origin
```

## 📊 Checking Remote Status

### Check what would be pushed:
```bash
git log origin/master..HEAD
```

### Check what would be pulled:
```bash
git fetch origin
git log HEAD..origin/master
```

### View remote repository info:
```bash
git remote show origin
```

---

**Next Action:** Create a remote repository and run the script to practice push/pull workflows!
