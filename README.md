CanaryUtil
==========

[![Build Status](https://ci.larry1123.net/job/CanaryUtil/badge/icon)](https://ci.larry1123.net/job/CanaryUtil/)  
[Latest Build](https://ci.larry1123.net/job/CanaryUtil/lastBuild/)  
[Latest Successful Build](https://ci.larry1123.net/job/CanaryUtil/lastSuccessfulBuild/)  
[CanaryMod Nest Page](http://canarymod.net/plugins/canaryutil/)  

This is a Plugin that adds some convince methods and tries to help make plugin development a little simpler.

CanaryUtil's api is is still in a beta state. The plugin is stable but the api is still young and may change.

What it contains:

* Fast and easy command registering without @Command for commands on the fly
* Fast and dynamic Sub Command handling
* Extended Logger
* Logs your plugin's Logs to there own file
* BungeeCord API, for all your proxy needs
* Config File api
* [PasteBin Logging](https://paste.larry1123.net/)!
* Logger will keep log files based on Hour, Day, Week, Month, or a CatchAll file
* String to Time util

Downloads:
============

Releases
------------

- [0.3.3](https://public.repo.larry1123.net/net/larry1123/CanaryUtil/0.3.3/CanaryUtil-0.3.3-shaded.jar) 
[md5](https://public.repo.larry1123.net/net/larry1123/CanaryUtil/0.3.3/CanaryUtil-0.3.3-shaded.jar.md5)
[sha1](https://public.repo.larry1123.net/net/larry1123/CanaryUtil/0.3.3/CanaryUtil-0.3.3-shaded.jar.sha1)
    * [GitHub Source](https://github.com/Larry1123/CanaryUtil/releases/tag/0.3.3)
    * [GitLab Source](https://git.larry1123.net/Larry1123/canaryutil/tree/0.3.3)
  

Latest Builds
------------

*Stable*

[Jenkins Build](https://ci.larry1123.net/job/CanaryUtil/lastStableBuild/)

*Bleeding*

[Jenkins Build](https://ci.larry1123.net/job/CanaryUtil/lastSuccessfulBuild/)

Plugin Development
=============

Maven
-------------

*Dependency*

        <dependency>
            <groupId>net.larry1123</groupId>
            <artifactId>CanaryUtil</artifactId>
            <version>[0.3.3,)</version>
            <!-- Will always use the latest version found (including snapshots)-->
        </dependency>


*Repository:*

        <repository>
            <id>repo1123</id>
            <name>Larry1123's Repo</name>
            <url>http://repo.larry1123.net/content/groups/public/</url>
        </repository>

Canary.inf
-------------

        dependencies=CanaryUtil

On top of this have users know that they need to have CanaryUtil as a plugin on their server also.

Java Docs
-------------

Primary: https://docs.larry1123.net/canaryutil/
Bleeding: https://ci.larry1123.net/job/CanaryUtil/javadocs/

Development
=============

*Git Repos*

- [GitHub](https://github.com/Larry1123/CanaryUtil)
    - SSH clone URL: git@github.com:Larry1123/CanaryUtil.git
    - HTTPS clone URL: https://github.com/Larry1123/CanaryUtil.git
    - Subversion checkout URL: https://github.com/Larry1123/CanaryUtil
- [GitLab](https://git.larry1123.net/Larry1123/canaryutil)
    - SSH clone URL: git@git.larry1123.net:Larry1123/canaryutil.git
    - HTTPS clone URL: https://git.larry1123.net/Larry1123/canaryutil.git

*Issues*

As a note please use the Latest [CanaryUtil](https://ci.larry1123.net/job/CanaryUtil/lastSuccessfulBuild/) if you are going to be reporting any bugs, as they may have been fixed.

- [GitHub](https://github.com/Larry1123/CanaryUtil/issues)
- [GitLab](https://git.larry1123.net/Larry1123/canaryutil/issues)
