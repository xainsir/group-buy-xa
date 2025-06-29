
步骤 1：切换到目标分支
```Bash
git checkout xxx
```

步骤 2：推送分支到 origin2

```Bash
git push origin2 xxx
```

步骤 3（可选）：设置上游分支
若希望后续直接使用 git push 而无需指定分支，设置上游分支：

```Bash
git push -u origin2 2-3-250628-multiThreadLoad
```