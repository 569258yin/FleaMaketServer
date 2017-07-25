# FileMaketServer
校园二手市场服务器端

        本服务器端适用于[校园二手市场APP](https://github.com/569258yin/FleaMarket),采用ssm+mysql进行实现。


1. 服务器端与客户端通信采用DES + MD5进行加密处理，并利用spring拦截器进行鉴权。
2. 对接环信云即时聊天系统，暂支持账号注册和文字聊天通信。
3. 对接短信包短息服务平台，用于注册时验证手机号。
4. 实现基于Token的登录模式
5. 文件上传，文件名及文件夹名生成策略。
6. 利用IKAnalyzer中文分词技术进行搜索处理，在数据库中建立全文索引（需要数据库引擎为MyISAM,并需要修改my.ini中的属性ft_min_word_len = 1）

