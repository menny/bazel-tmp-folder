# How to set up tmp folder inside bazel actions
In `.bazelrc` I've set:
```
build --sandbox_tmpfs_path=/tmp1
test --test_tmpdir=/tmp1
common --action_env=TMP=/tmp1 --action_env=TEMP=/tmp1 --action_env=TMPDIR=/tmp1
```
I assumed that this will force Bazel use `/tmp1` as temporary folder. To check that I created a `genrule` that prints out
environment variables and uses `mktemp` to create a temp file. I did not get the expected results:
```
~/dev/menny/bazel-tmp-dir (main ✗) bazel build //:env_builder_cmd && cat bazel-bin/env_cmd.txt
INFO: Analyzed target //:env_builder_cmd (0 packages loaded, 0 targets configured).
INFO: Found 1 target...
Target //:env_builder_cmd up-to-date:
  bazel-bin/env_cmd.txt
INFO: Elapsed time: 0.046s, Critical Path: 0.00s
INFO: 1 process: 1 internal.
INFO: Build completed successfully, 1 total action
PWD=/home/menny/.cache/bazel/_bazel_menny/33f202e777fb78b580b8a1dd0558ff57/execroot/__main__
TMPDIR=/tmp
TEMP=/tmp1
SHLVL=1
TMP=/tmp1
PATH=/bin:/usr/bin:/usr/local/bin
_=/bin/env
mktemp is /tmp/tmp.AwJMnOpnw3
```

So, interestingly, `TMPDIR` is pointing to `/tmp`, which is what my local machine is using. If I run this on a macOS, I will get something like `/var/folders/tq/vxkgqjw51g3_w47k_vjx6y4h0000gn/T/tmp.CawQvw9s`
