通过MyBatis-Plus快速搭建好项目框架(wechat vod user order activity模块)
使用SpringCloud的getway、nacos、OpenFeign组件搭建好网关解决跨域问题、动态服务发现、各模块间的调用。
common 模块构建全局异常处理机制、统一的json数据格式、各个表对应的实体类、各种工具类。
vod 模块 对讲师、学科、课程、章节、视频等表进行操作，其中视频的储存调用腾讯云的云点播接口、讲师的头像储存依
托于腾讯云的对象存储、学科表设置parent_id字段方便在前端进行树形数据的方式展现，通过EasyExcel对学科进行导入与
导出、视频观看人数用Echatrs进行展示、分页用的是MyBatis-Plus的分页插件等。
activity模块 对优惠卷的增删改操作，其中对user模块进行调用把用户的手机号和昵称与优惠卷信息进行封装返回前端。
order、user 模块 对订单、用户表进行操作，其中使用weixin-java-mp jar包进行对微信公众号进行用户授权。
wechat模块 对菜单表进行操作，编写微信公号菜单封装返回、微信服务器有效性验证、接收微信服务器发送来的消息等接
口。
