# Git Branching and Merging - Task 3

## 📁 Project Structure
```
Task 3/
├── README.md                      # This file - Getting started guide
├── QUICK-REFERENCE.md             # Quick cheat sheet
├── Git-Branch-Commands.md         # Complete command reference
├── run-git-branch-lab.ps1         # Automated PowerShell script
└── GitBranchDemo/                 # Git repository folder
    ├── .git/                      # Git metadata (hidden)
    └── main.txt                   # Initial file
```

## 🎯 Objectives

- Explain branching and merging concepts
- Construct a branch and make changes
- Merge branch with master (trunk)
- Create branch requests in GitLab
- Create merge requests in GitLab
- Use P4Merge tool for visual diff (optional)

## 🚀 Quick Start

### Option 1: Run Automated Script (Recommended)
Open PowerShell in the "Task 3" folder and run:
```powershell
.\run-git-branch-lab.ps1
```

### Option 2: Manual Step-by-Step

#### Part 1: Branching

1. **Navigate to repository**
   ```bash
   cd GitBranchDemo
   ```

2. **Create a new branch**
   ```bash
   git branch GitNewBranch
   ```

3. **List all branches**
   ```bash
   git branch -a
   ```
   The `*` marks your current branch.

4. **Switch to the new branch**
   ```bash
   git checkout GitNewBranch
   # Or use: git switch GitNewBranch (newer command)
   ```

5. **Create and add files**
   ```bash
   echo "Feature content" > feature.txt
   git add feature.txt
   git commit -m "Added feature.txt in GitNewBranch"
   ```

6. **Check status**
   ```bash
   git status
   ```

#### Part 2: Merging

1. **Switch back to master**
   ```bash
   git checkout master
   ```

2. **View differences (text)**
   ```bash
   git diff master..GitNewBranch
   ```

3. **View differences (P4Merge - optional)**
   ```bash
   git difftool master..GitNewBranch
   ```

4. **Merge the branch**
   ```bash
   git merge GitNewBranch
   ```

5. **View merge history with graph**
   ```bash
   git log --oneline --graph --decorate
   ```

6. **Delete the branch**
   ```bash
   git branch -d GitNewBranch
   ```

7. **Verify deletion**
   ```bash
   git branch
   git status
   ```

## 📝 What is Branching?

**Branching** allows you to diverge from the main line of development and continue to work without affecting the main line. Think of it as creating a parallel universe where you can experiment safely.

### Use Cases:
- **Features**: Develop new features in isolation
- **Bug Fixes**: Fix bugs without affecting production code
- **Experiments**: Try new ideas without risk
- **Releases**: Maintain different versions

### Common Branch Names:
- `master` or `main` - Primary branch
- `develop` - Development branch
- `feature/login` - Feature branches
- `bugfix/issue-123` - Bug fix branches
- `hotfix/security` - Emergency fixes

## 🔀 What is Merging?

**Merging** combines the changes from one branch into another. It integrates the work you've done in a separate branch back into your main codebase.

### Merge Types:
- **Fast-Forward**: Simple merge when no divergent changes
- **Three-Way Merge**: When both branches have changes
- **Squash Merge**: Combines all commits into one

## 📋 Lab Tasks Checklist

### Branching:
- [x] Git repository initialized
- [x] Initial files created
- [ ] Create new branch "GitNewBranch"
- [ ] List all branches
- [ ] Switch to new branch
- [ ] Add files to branch
- [ ] Commit changes
- [ ] Check status

### Merging:
- [ ] Switch back to master
- [ ] View text differences
- [ ] View visual differences (P4Merge)
- [ ] Merge branch to master
- [ ] View merge graph
- [ ] Delete merged branch
- [ ] Verify final status

## 🛠️ Git Branch Commands Reference

| Command | Purpose |
|---------|---------|
| `git branch` | List local branches |
| `git branch <name>` | Create new branch |
| `git branch -a` | List all branches (local & remote) |
| `git checkout <name>` | Switch to branch |
| `git switch <name>` | Switch to branch (newer) |
| `git checkout -b <name>` | Create and switch to branch |
| `git merge <branch>` | Merge branch into current |
| `git branch -d <name>` | Delete merged branch |
| `git branch -D <name>` | Force delete branch |

## 🔀 Git Merge Commands Reference

| Command | Purpose |
|---------|---------|
| `git merge <branch>` | Merge branch to current |
| `git merge --no-ff <branch>` | Force merge commit |
| `git merge --abort` | Cancel merge if conflicts |
| `git diff <branch1>..<branch2>` | Compare branches |
| `git difftool <branch1>..<branch2>` | Visual comparison |
| `git log --graph` | Show branch graph |
| `git log --oneline --graph --decorate` | Detailed graph |

## 💡 Important Concepts

### Current Branch
The branch you're currently on (marked with `*` in `git branch` output).

### HEAD
A pointer to your current branch and commit.

### Master/Main
The default primary branch (older repos use `master`, newer use `main`).

### Fast-Forward Merge
When the target branch hasn't changed, Git just moves the pointer forward.

### Merge Commit
A special commit that has two parents, combining two branches.

## ⚙️ Optional: P4Merge Setup (Windows)

P4Merge is a visual diff and merge tool.

### Installation:
1. Download from: https://www.perforce.com/downloads/visual-merge-tool
2. Install P4Merge
3. Configure Git to use P4Merge:

```bash
git config --global merge.tool p4merge
git config --global mergetool.p4merge.path "C:/Program Files/Perforce/p4merge.exe"
git config --global diff.tool p4merge
git config --global difftool.p4merge.path "C:/Program Files/Perforce/p4merge.exe"
```

### Usage:
```bash
# View diff visually
git difftool master..GitNewBranch

# Resolve merge conflicts visually
git mergetool
```

## ⏱️ Estimated Time
30 minutes

## 👤 Student Information
- **Name:** PratapSakthivel
- **Email:** pratapssakthivel@gmail.com

## ✅ Expected Results

### After Branching:
```bash
$ git branch
  master
* GitNewBranch
```

### After Merging:
```bash
$ git log --oneline --graph --decorate
* abc1234 (HEAD -> master) Merge branch 'GitNewBranch'
|\
| * def5678 Added feature.txt in GitNewBranch
|/
* 123abcd Initial commit
```

### After Deleting Branch:
```bash
$ git branch
* master
```

## 🌐 GitLab Integration

### Create Branch in GitLab:
1. Go to your GitLab project
2. Click Repository → Branches
3. Click "New branch"
4. Enter branch name
5. Select source branch
6. Click "Create branch"

### Create Merge Request:
1. Push your local branch: `git push origin GitNewBranch`
2. Go to GitLab project
3. Click "Create merge request"
4. Select source and target branches
5. Add title and description
6. Assign reviewers
7. Click "Create merge request"

---

**Next Action:** Run the automated script or follow the manual steps above!
