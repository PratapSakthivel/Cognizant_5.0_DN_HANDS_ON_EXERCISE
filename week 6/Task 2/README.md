# Git Ignore Hands-On Lab - Task 2

## 📁 Project Structure
```
Task 2/
├── README.md                  # This file - Getting started guide
├── Git-Ignore-Commands.md     # Complete command reference
├── run-git-ignore-lab.ps1     # Automated PowerShell script
└── GitIgnoreDemo/             # Git repository folder
    ├── .git/                  # Git metadata (hidden)
    ├── .gitignore             # Git ignore configuration
    ├── app.log                # Log file (ignored)
    ├── logs/                  # Log folder (ignored)
    │   └── debug.log
    └── main.txt               # Regular file (tracked)
```

## 🎯 Objectives

- Explain git ignore
- Explain how to ignore unwanted files using git ignore
- Implement git ignore command to ignore unwanted files and folders

## 🚀 Quick Start

### Option 1: Run Automated Script (Recommended)
Open PowerShell in the "Task 2" folder and run:
```powershell
.\run-git-ignore-lab.ps1
```

### Option 2: Manual Step-by-Step

1. **Navigate to GitIgnoreDemo folder**
   ```bash
   cd GitIgnoreDemo
   ```

2. **Create log file and log folder**
   ```bash
   echo "Error log entry" > app.log
   mkdir logs
   echo "Debug log entry" > logs/debug.log
   ```

3. **Check Git status (before .gitignore)**
   ```bash
   git status
   ```
   You'll see app.log and logs/ as untracked files.

4. **Create .gitignore file**
   ```bash
   echo "*.log" > .gitignore
   echo "logs/" >> .gitignore
   ```

5. **Check Git status (after .gitignore)**
   ```bash
   git status
   ```
   Now only .gitignore appears as untracked!

6. **Add and commit .gitignore**
   ```bash
   git add .gitignore
   git commit -m "Added .gitignore to ignore log files and folders"
   ```

7. **Verify final status**
   ```bash
   git status
   ```

## 📝 What is .gitignore?

`.gitignore` is a special file that tells Git which files or folders to ignore in a project. Files listed in `.gitignore` will not be:
- Tracked by Git
- Shown in `git status`
- Added to commits
- Pushed to remote repositories

### Common Use Cases:
- Log files (*.log)
- Build outputs (dist/, build/)
- Dependencies (node_modules/, vendor/)
- IDE files (.vscode/, .idea/)
- Environment files (.env)
- Temporary files (*.tmp, *.cache)

## 🔍 .gitignore Patterns

| Pattern | Description | Example |
|---------|-------------|---------|
| `*.log` | Ignore all .log files | app.log, error.log |
| `logs/` | Ignore logs directory | logs/ folder |
| `temp.*` | Ignore files starting with temp | temp.txt, temp.log |
| `!important.log` | Exception - do NOT ignore | Tracks important.log |
| `**/cache` | Ignore cache in any directory | src/cache, lib/cache |

## 📋 Lab Tasks Checklist

- [x] Git repository initialized
- [x] .gitignore file created
- [x] Log file (.log) created
- [x] Log folder created
- [ ] Run automated script
- [ ] Verify git status before .gitignore
- [ ] Add patterns to .gitignore
- [ ] Verify git status after .gitignore
- [ ] Commit .gitignore

## 🛠️ Git Commands Reference

| Command | Purpose |
|---------|---------|
| `git status` | Check which files are tracked/untracked |
| `git add .gitignore` | Stage .gitignore file |
| `git commit -m "message"` | Commit the .gitignore |
| `git rm --cached <file>` | Untrack already tracked file |
| `git check-ignore -v <file>` | Check why a file is ignored |

## 💡 Important Notes

1. **Files already tracked:** If a file is already tracked by Git, adding it to .gitignore won't untrack it. Use:
   ```bash
   git rm --cached <filename>
   ```

2. **Folder patterns:** Always end folder patterns with `/` (e.g., `logs/`)

3. **Global .gitignore:** You can create a global .gitignore for your system:
   ```bash
   git config --global core.excludesfile ~/.gitignore_global
   ```

## ⏱️ Estimated Time
20 minutes

## 👤 Student Information
- **Name:** PratapSakthivel
- **Email:** pratapssakthivel@gmail.com

## ✅ Expected Results

### Before .gitignore:
```
Untracked files:
  app.log
  logs/
```

### After .gitignore:
```
Untracked files:
  .gitignore
```

The log files and folders are now ignored!

---

**Next Action:** Run the automated script or follow the manual steps above.
