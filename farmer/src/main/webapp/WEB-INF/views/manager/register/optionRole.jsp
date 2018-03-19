<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1,user-scalable=no">
    <title>选择角色</title>
    <link rel="stylesheet" href="${ctxStatic}/css/cssreset-min.css">
    <link rel="stylesheet" href="${ctxStatic}/css/login.css"/>
    <script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${ctxStatic}/layer/layer.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $(".regis").click(function () {
                var name = $("[name='role']:checked").val();
                if (name != "farmerBoss") {
                    var url = "${ctx}/reg/register?eName=" + name;
                    $(".form-signin").attr("action", url);
                    $(".form-signin").submit();
                }
                if (name == "farmerBoss") {
                    var fName = $("[name='farmerName']").val();
                    if (fName == null || fName == "") {
                        layer.msg("请输入公司名称！");
                    }
                    else {
                        $.ajax({
                            url: "${ctx}/reg/checkOfficeName",
                            data: {
                                officeName: fName
                            },
                            dataType: "JSON",
                            success: function (result) {
                                if (result.flag == 0) {
                                    layer.msg("已有该公司名称，请重新输入名称");
                                } else {
                                    var url = "${ctx}/reg/register?eName=" + name;
                                    $(".form-signin").attr("action", url);
                                    $(".form-signin").submit();
                                }
                            }
                        });

                    }
                }
            });
        });
        function noShowF() {
            $(".show").addClass("noshow");
        }
        function showF() {
            $(".show").removeClass("noshow");
        }
        function f1() {
            var v = $("[name='farmerName']").val();
            if (v == null || v == "") {
                layer.msg("请输入公司名称！");
            } else {
                $.ajax({
                    url: "${ctx}/reg/checkOfficeName",
                    data: {
                        officeName: v
                    },
                    dataType: "JSON",
                    success: function (result) {
                        if (result.flag == 0) {
                            layer.msg("已有该公司名称，请重新输入名称");
                        }
                    }
                });
            }
        }
    </script>
</head>
<body class="body">
<div class="header">
    <div class="he_left"><img src="${ctxStatic}/images/no-3.png"/></div>
    <div class="he_rigth"><a href="${ctx}">登录</a> | <a href="${ctx}/reg/optionRole">注册</a></div>
    <div class="clearfix"></div>
</div>
<div class="contPassword">
    <form class="form-signin" action="${ctx}/reg/modifyPassword" method="post">
        <div class="texts2">
            选择角色
        </div>
        <div class="nc">
            <span>&nbsp;&nbsp;&nbsp;农场主：<input type="radio" name="role" class="ra" value="farmerBoss" checked="checked"
                                               onchange="showF()"/></span>
            <span>农  户：<input type="radio" name="role" value="farmerWorker" onchange="noShowF()"/></span>
            <div class="clearfix"></div>
        </div>
        <div class="show">
            公司名称：<input type="text" name="farmerName" htmlEscape="false" maxlength="50" class="required" onblur="f1()"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
        <!--	<div class="regis">
            <div onclick="form1.submit()"><a>注册</a></div>
            </div>-->
        <input type="button" class="regis" value="下一步"/>
    </form>
</div>
<div class="footer">
    Copyright &copy;大唐移动通信设备有限公司
</div>
</body>
</html>
