# KBlogger

A blog launch by Ktor.

## Quick Use

Just set `config.yaml` and run it with Docker(Recommend). Below is a sample:

``` yaml
site_title: TonyPepe Blog
site_author: Tonypepe
git_repo_url: https://github.com/TonyPepeBear/MdNotebook

author_github_url: https://github.com/TonyPepeBear
author_facebook_url: https://github.com/TonyPepeBear
author_twitter_url: https://github.com/TonyPepeBear
```

KBlogger will clone all files in GitRepo and render all of the `*.md` file.

Your markdown file must have `yaml-front-matter`. And must have `title` tag, if not, that file will be ignored.

``` markdown
---
title: Post Title
preview: A sample post
date: 2021/05/20
tag:
    - kblogger
    - post
    - blog
---

This is Sample Post.

## H2

```

### Run With Docker

[Docker Hub](https://hub.docker.com/r/tonypepe/kblogger)

``` bash
docker run -p 8080:8080 \
    -v ${PWD}/config.yaml:/project/bin/config.yaml \
    tonypepe/kblogger 
```

Now go to `localhost:8080` and see your website.

