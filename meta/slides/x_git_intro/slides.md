---
layout: cover
---

![0_msu_title.png](0_msu_title.png)


---
layout: center
---

# Version Control - Learning Git
## Instructor: Carson Gross
## carson.gross@montana.edu


---
layout: center
---

## What is Version Control

“Version control is a system that records changes to a file or set of files over time so that you can recall specific versions later.”


---
layout: default
---

## In the Beginning

* The Stone Age
  * cp and diff
  * emailing diff patches around
* Early Version Control Systems - Centralized
  * [cvs](https://www.gnu.org/software/trans-coord/manual/cvs/cvs.html) - Concurrent Versioning System
  * [subversion](https://subversion.apache.org/)
  * [perforce](https://www.perforce.com/) - commercial
* One source of truth, difficult to work “offline”


---
layout: two-cols-header
---

## Today

::left::
* Distributed Version Control Systems (DVCS)
  * No locking!
  * No central repository!
  * Autonomy!
* git and mercurial (hg)
* git won
  * I don't know why

::right::
![1_vcs_today.png](/1_vcs_today.png)


---
layout: two-cols-header
---

## Today

::left::
* About that "No central repository!"  Welllllllll...
* Github has become the central master repository: http://github.com

::right::
![2_vcs_today_2.png](/2_vcs_today_2.png)


---
layout: two-cols-header
---

## What Is a Github?

::left::
* Github is a site that hosts git repositories
  * It provides a nice web interface for git repos
  * Many web-based tools
    * Automated testing
    * Deploying
    * A whole eco-system...

::right::
![2_vcs_today_2.png](/2_vcs_today_2.png)


---
layout: two-cols-header
---

## What is a Github?

::left::
* Then Microsoft bought them :(
* There are alternatives!
  * Gitlab: https://about.gitlab.com/
  * My fav: https://sr.ht/
  * We aren't going to use them :(
* Github is the standard & has become important for a job

::right::
![2_vcs_today_2.png](/2_vcs_today_2.png)


---
layout: two-cols-header
---

## What Is a Github?

::left::
* Github is, ironically, very popular with open source
  * But not Richard Stallman!
* Github allows you to “fork” repositories easily
* You can contribute changes back via "pull requests"
* Contributing to Open Source projects is much, much easier

::right::
![3_github_microsoft.png](/3_github_microsoft.png)


---
layout: two-cols-header
---

## What Is a Github?

::left::
* “Forks” and "Pull requests" are a Github thing, not a git thing
* But we'll get to all that later. For right now, just make sure you have a github account set up:  https://github.com/

::right::
![3_github_microsoft.png](/3_github_microsoft.png)


---
layout: default
---

## Git

* Git is a command line tool for managing changes to source code
* Developed by Linus Torvalds, of Linux Fame
  * Linux Kernel was  using a proprietary source control system for Linux, BitKeeper
  * BitKeeper guy got mad, pulled the free license
  * Linus declared he would begin working on git on April 3rd, 2005
  * First version shipped April 6th
  * Self-hosted April 7th
  * Managed the Linux 2.6.12 release on June 16th
  * Linus is a really good programmer...

::right::
![3_github_microsoft.png](/3_github_microsoft.png)


---
layout: two-cols-header
---

## Git Concepts

::left::
* Repository (repo) - A collection of code with a URL end point
  * e.g. https://github.com/bigskysoftware/htmx.git  or       git@github.com:bigskysoftware/htmx.git
* Clone - A clone (copy) of another repo (the “upstream” repo)
* Remote - A remote copy of a repo (e.g. the “upstream” repo)
* Branch - An independent copy within a given repo
  * A repo can have multiple branches, all with different content
* Merging & Rebasing - Ways to get changes from one repo into another
* It's a lot, I know.


---
layout: two-cols-header
---

## Git Intro - clone

::left::
* How to I get code from a remote repository onto my machine?
* The clone command - clones a repository to your local machine
* You can now make changes to the code locally

::right::
![4_git_clone.png](/4_git_clone.png)


---
layout: default
---

## More Git Concepts

* Working Directory - The current state of code on your machine
* Staging Area - Changes you have “added” to be committed to the repository
* Repository - What the repository knows about


---
layout: two-cols-header
---

## Git Intro - add

::left::
* How do I get changes into my local repository?
* You “stage” them with “git add”
* You “commit” them with “git commit”

::right::
![5_git_add_commit.png](/5_git_add_commit.png)


---
layout: two-cols-header
---

## Git Intro - add

::left::
* Edit a file EXAMPLE.txt
* Run “git status”, note that it is “untracked”
* Use “git add” to stage your modification to EXAMPLE.txt
  * Again, stage means "ready to commit"
  * “git add” can also add new files, not just modified files

::right::
![6_git_add_example.png](/6_git_add_example.png)

---
layout: two-cols-header
---

## Git Intro - commit

::left::
* OK, let's commit our change
* We are committing it to the local repository
  * We are NOT sending it up to the server we cloned from

::right::
![7_git_commit.png](/7_git_commit.png)


---
layout: two-cols-header
---

## Git Intro - commit

::left::
* What's this?
  * Remember pico?
* You need to create a “commit message”
* By default git will use the system editor, which is good old pico in our case
  * You can override this
* Write a message and save...

::right::
![8_commit_message.png](/8_commit_message.png)


---
layout: two-cols-header
---

## Git Intro - commit

::left::
* And its saved!
* git now knows about your new file
* If you wanted, you could roll back to a version before your file was created.

::right::
![9_git_commit_confirmation.png](/9_git_commit_confirmation.png)


---
layout: two-cols-header
---

## Git Intro - push

::left::
* OK, so how do I get changes into the remote repository?
* You “push” them to the remote repository with “git push”

::right::
![10_git_push_pull.png](10_git_push_pull.png)


---
layout: two-cols-header
---

## Git Intro - pull

::left::
* What about new changes from the remote repository
* You “pull” them from the remote repository with “git pull”

::right::
![10_git_push_pull.png](10_git_push_pull.png)


---
layout: two-cols-header
---

## Git Intro - branch

::left::
* Think of a branch as a new local copy of the code
  * It can be edited alongside other branches
  * Changes in the branch won't be visible in others until you merge
* Add a branch with: git branch \<branch-name\>

::right::
![11_git_branch.png](11_git_branch.png)


---
layout: two-cols-header
---

## Git Intro - branch

::left::
* Git branches have lead to a large number of "git branch strategies"
  * Conventions for branches that facilitate development
* There is no standard
* You will have one “main” branch
  * Traditionally it is called the "master" branch

::right::
![11_git_branch.png](11_git_branch.png)


---
layout: two-cols-header
---

## Git Intro - branch

::left::
* You often have one or more "development" branches
* you might also have "feature" branches
* You might have "release" branches
* It can get pretty crazy
* We will keep it simple for this class: you will have one branch

::right::
![11_git_branch.png](11_git_branch.png)


---
layout: two-cols-header
---

## Git Intro - remote

::left::
* What if I want to pull from another remote repository?
  * For example, from the class repository? ;)
* Add the remote with: git remote add \<name> \<url\>
* Now you can pull from it with: git pull \<name\> \<branch\>

::right::
![12_git_remote.png](12_git_remote.png)


---
layout: two-cols-header
---

## Git Intro - remote

::left::
* With remotes, you are usually only pushing to one
* The other remotes are “upstream” repos you only pull from

::right::
![12_git_remote.png](12_git_remote.png)


---
layout: default
---

## Other Git Commands

* git diff - compare objects
* git revert - undo changes but save as a new commit
* git cherry-pick - merge with specific commits
* git tag - add a tag to a commit
* git rebase - change base (HEAD^)
* git reset - undo changes to a previous commit


---
layout: default
---

## Whew!

* Yes, it's a lot
* Don't worry, we will be available to help you get all set up
* The course will be set up so that you can avoid most of this
  * One upstream repo
  * One branch per student (your email as the branch name)
  * All you have to do is pull from the upstream master branch, and push to your fork
    * We will talk about forks next time
  * It won't be that bad, I promise


---
layout: default
---

## Some Git Resources

* Git Cheat Sheet:
  * https://github.github.com/training-kit/downloads/github-git-cheat-sheet

* Git - The Simple Guide:
  * https://rogerdudler.github.io/git-guide/


---
layout: center
---

## Some Git Realtalk

![13_git_realtalk.png](13_git_realtalk.png)


---
layout: default
---

## Some Git Realtalk

* I find git painful to deal with on the command line, even after years of using it
* I usually only use the command line for simple stuff
* For complicated things like merges, JetBrains has a great UI that makes things much easier


---
layout: center
---

## IntelliJ Demo - Avoiding Learning Git


---
layout: end
---

![0_msu_title.png](0_msu_title.png)