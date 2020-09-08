# SUI Mobile for jQuery

## 分支说明
由于淘宝M-SUI不再更新，这里只能Fork M-SUI并进行少量必要的维护，以满足当前的需要！

## 轻量的UI库

[SUI Mobile](http://sui.ctolog.com/) 是阿里巴巴国际UED前端团队出品的移动端  __UI库__。

没有重复造轮子。主要借鉴 __Framework7__ 的形态并以此为基础，参考Ratchet、Ionic、Onsen各种不同的思路，强化功能，精简体积，并提供国内最稳定快速的CDN支持。此外还定制增强了一些工具类开源库。

它的特点是非常轻量，并且能很好兼容 iOS 6.0+ 和 Android 4.0+  的设备。

更多信息请前往 [官网](http://sui.ctolog.com)

## 问题反馈

首先在[官网FAQ页](http://sui.ctolog.com/faq/)看下常见疑问里有没有你的问题。     
其次在[issue](http://git.oschina.net/zoujingli/sui-mobile/issues)里搜索一下你的问题关键字，可能之前已经有人遇到并解决了，可以节省大量时间精力。

明确的问题更__推荐__直接提[issue反馈](http://git.oschina.net/zoujingli/sui-mobile/issues)。

### Issue 注意

考虑到移动端的特性，请一定写明 __系统、机型、浏览器的型号和版本__


## 开发环境搭建

安装 nodejs, 配置 npm 命令可以全局使用（这里就不多介绍了）

在项目根目录下，安装相关依赖包，命令如下：
```
npm install
```

编译修改，命令如下：
```
grunt build
```

本地文档阅读，需要安装 jekyll
```
grunt server
```
