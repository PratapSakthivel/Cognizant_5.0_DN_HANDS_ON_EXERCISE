# Git Conflict Resolution - Task 4

## 📁 Project Structure
```
Task 4/
├── README.md                      # This file - Getting started guide
├── QUICK-REFERENCE.md             # Quick cheat sheet
├── Git-Conflict-Commands.md       # Complete command reference
├── run-git-conflict-lab.ps1       # Interactive PowerShell script
└── GitConflictDemo/               # Git repository folder
    ├── .git/                      # Git metadata (hidden)
    └── main.txt                   # Initial file
```

## 🎯 Objectives

- Explain how to resolve conflicts during merge
- Implement conflict resolution when multiple users update the same files
- Understand conflict markers and resolution strategies
- Use merge tools (P4Merge) for visual conflict resolution
- Handle backup files with .gitignore

## ⚠️ Prerequisites

- **Task 3 completion recommended** (branching and merging basics)
- Understanding of git merge workflow
- P4Merge tool (optional, for visual resolution)

## 🚀 Quick Start

### Option 1: Run Interactive Script (Recommended)
Open PowerShell in the "Task 4" folder and run:
```powershell
.\run-git-conflict-lab.ps1
```

### Option 2: Manual Step-by-Step

#### Part 1: Setup Conflict Scenario

1. **Verify master is clean**
   ```bash
   cd GitConflictDemo
   git status
   ```

2. **Create a branch "GitWork"**
   ```bash
   git branch GitWork
   git checkout GitWork
   ```

3. **Create and update hello.xml in branch**
   ```bash
   echo "<hello>Branch version</hello>" > hello.xml
   git add hello.xml
   git commit -m "Added hello.xml in GitWork branch"
   ```

4. **Switch to master**
   ```bash
   git checkout master
   ```

5. **Create conflicting hello.xml in master**
   ```bash
   echo "<hello>Master version</hello>" > hello.xml
   git add hello.xml
   git commit -m "Added hello.xml in master"
   ```

#### Part 2: Create and Resolve Conflict

6. **View log with graph**
   ```bash
   git log --oneline --graph --decorate --all
   ```

7. **Check differences**
   ```bash
   git diff master..GitWork
   ```

8. **Attempt merge (will conflict!)**
   ```bash
   git merge GitWork
   ```

9. **View conflict markers**
   ```bash
   cat hello.xml
   ```

#### Part 3: Resolve Conflict

10. **Option A: Manual resolution**
    - Open hello.xml
    - Remove conflict markers
    - Keep desired content
    - Save file

11. **Option B: Use merge tool**
    ```bash
    git mergetool
    ```

12. **Complete the merge**
    ```bash
    git add hello.xml
    git commit -m "Resolved conflict between master and GitWork"
    ```

13. **Add backup files to .gitignore**
    ```bash
    echo "*.orig" >> .gitignore
    git add .gitignore
    git commit -m "Added backup files to gitignore"
    ```

14. **Delete merged branch**
    ```bash
    git branch -d GitWork
    ```

15. **View final log**
    ```bash
    git log --oneline --graph --decorate
    ```

## 💥 Understanding Merge Conflicts

### What is a Merge Conflict?
A merge conflict occurs when Git cannot automatically resolve differences between two commits. This happens when:
- The same line in a file is modified differently in two branches
- A file is deleted in one branch but modified in another
- Files with the same name are added in both branches

### Conflict Markers:
```
<<<<<<< HEAD (Current Change)
Content in the current branch (master)
=======
Content in the branch being merged (GitWork)
>>>>>>> GitWork (Incoming Change)
```

### Resolution Steps:
1. Identify conflicted files (`git status`)
2. Open each file and find conflict markers
3. Decide which changes to keep
4. Remove conflict markers
5. Stage resolved files (`git add`)
6. Complete merge (`git commit`)

## 🛠️ Git Conflict Commands

| Command | Purpose |
|---------|---------|
| `git merge <branch>` | Merge branch (may cause conflict) |
| `git status` | See conflicted files |
| `git diff` | View differences |
| `git mergetool` | Open visual merge tool |
| `git merge --abort` | Cancel merge and return to pre-merge state |
| `git add <file>` | Mark conflict as resolved |
| `git commit` | Complete merge after resolution |

## 🎨 Merge Tool (P4Merge)

### Setup P4Merge:
```bash
git config --global merge.tool p4merge
git config --global mergetool.p4merge.path "C:/Program Files/Perforce/p4merge.exe"
```

### Use P4Merge:
```bash
git mergetool
```

P4Merge shows:
- **LEFT**: Local (current branch)
- **RIGHT**: Remote (merging branch)
- **BASE**: Common ancestor
- **BOTTOM**: Result (your resolution)

## 📋 Lab Tasks Checklist

- [x] Git repository initialized
- [x] Initial files created
- [ ] Verify master is clean
- [ ] Create GitWork branch
- [ ] Add hello.xml to branch
- [ ] Commit to branch
- [ ] Switch to master
- [ ] Add conflicting hello.xml
- [ ] Commit to master
- [ ] View log graph
- [ ] Attempt merge (conflict!)
- [ ] Resolve conflict
- [ ] Add backup files to .gitignore
- [ ] Complete merge
- [ ] Delete merged branch

## ⚠️ Common Conflict Scenarios

### Scenario 1: Same Line Modified
```
Branch A: Hello World!
Branch B: Hello Universe!
Result: CONFLICT
```

### Scenario 2: File Deleted vs Modified
```
Branch A: Deletes file.txt
Branch B: Modifies file.txt
Result: CONFLICT
```

### Scenario 3: Rename Conflicts
```
Branch A: Renames file1.txt to fileA.txt
Branch B: Renames file1.txt to fileB.txt
Result: CONFLICT
```

## ✅ Resolution Strategies

### Strategy 1: Keep Ours (Current Branch)
```bash
git checkout --ours hello.xml
git add hello.xml
```

### Strategy 2: Keep Theirs (Merging Branch)
```bash
git checkout --theirs hello.xml
git add hello.xml
```

### Strategy 3: Manual Edit
Edit file, remove markers, keep both/either/neither, then:
```bash
git add hello.xml
```

### Strategy 4: Use Merge Tool
```bash
git mergetool
# Follow GUI prompts
git add hello.xml
```

## ⏱️ Estimated Time
30 minutes

## 👤 Student Information
- **Name:** PratapSakthivel
- **Email:** pratapssakthivel@gmail.com

## ✅ Expected Results

### Before Merge (Graph):
```
* commit3 (GitWork) Added hello.xml in branch
| * commit2 (HEAD -> master) Added hello.xml in master
|/
* commit1 Initial commit
```

### During Conflict:
```
$ git merge GitWork
CONFLICT (add/add): Merge conflict in hello.xml
Automatic merge failed; fix conflicts and then commit the result.
```

### After Resolution:
```
*   commit4 (HEAD -> master) Resolved conflict
|\
| * commit3 Added hello.xml in branch
* | commit2 Added hello.xml in master
|/
* commit1 Initial commit
```

## 💡 Best Practices

1. **Pull before merge** - Get latest changes first
2. **Communicate** - Coordinate with team members
3. **Small commits** - Easier to resolve conflicts
4. **Test after resolution** - Ensure code still works
5. **Use merge tools** - Visual tools help understand conflicts
6. **Don't panic** - Conflicts are normal in collaboration

## 🔧 Troubleshooting

### Stuck in merge state?
```bash
git merge --abort
```

### Want to start over?
```bash
git reset --hard HEAD
```

### Accidentally committed with conflict markers?
```bash
git reset --soft HEAD~1
# Fix the file
git add file
git commit
```

---

**Next Action:** Run the interactive script to practice conflict resolution!
