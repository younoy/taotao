<%--
  Created by IntelliJ IDEA.
  User: upsmart
  Date: 17-12-16
  Time: 下午3:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Cache-Control" content="max-age=300" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>${query} - 商品搜索 - 淘淘</title>
    <meta name="Keywords" content="java,淘淘java" />
    <meta name="description" content="在淘淘中找到了29910件java的类似商品，其中包含了“图书”，“电子书”，“教育音像”，“骑行运动”等类型的java的商品。" />
    <link rel="stylesheet" type="text/css" href="/css/base.css" media="all" />
    <link rel="stylesheet" type="text/css" href="/css/psearch20131008.css" media="all" />
    <link rel="stylesheet" type="text/css" href="/css/psearch.onebox.css" media="all" />
    <link rel="stylesheet" type="text/css" href="/css/pop_compare.css" media="all" />
    <link rel="stylesheet" type="text/css" href="/css/saved_resource.css">
    <link rel="stylesheet" type="text/css" href="/css/addtocart-album.css">
    <script type="text/javascript" src="/js/jquery-1.6.4.js"></script>
    <link rel="stylesheet" type="text/css" href="./商品已成功加入购物车_files/saved_resource(2)"><style id="style-1-cropbar-clipper">
    /* Copyright 2014 Evernote Corporation. All rights reserved. */
    .en-markup-crop-options {
        top: 18px !important;
        left: 50% !important;
        margin-left: -100px !important;
        width: 200px !important;
        border: 2px rgba(255,255,255,.38) solid !important;
        border-radius: 4px !important;
    }

    .en-markup-crop-options div div:first-of-type {
        margin-left: 0px !important;
    }
</style>
</head>
<body>
<script type="text/javascript" src="/js/base-2011.js" charset="utf-8"></script>
<!-- header start -->
<jsp:include page="commons/header.jsp" />
<div class="main">
    <div class="success-wrap">
        <div class="w" id="result">
            <div class="m succeed-box">
                <div class="mc success-cont">
                    <div class="success-lcol">
                        <div class="success-top">
                            <b class="succ-icon"></b>
                                <h3 class="ftx-02">商品已成功加入购物车！</h3>
                        </div>
                    </div>
                    <br/>
                    <br/>
                    <div class="success-btns success-btns-new">
                        <div>
                            <a class="btn-addtocart" href="http://localhost:8082/cart/cart.html" id="GotoShoppingCart" clstag="pageclick|keycount|201601152|4">
                                去购物车结算
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- footer start -->
<jsp:include page="commons/footer.jsp" />
<!-- footer end -->
<script type="text/javascript" src="/js/jquery.hashchange.js"></script>
<script type="text/javascript" src="/js/search_main.js"></script>
<script type="text/javascript">
    ${paginator.totalPages}
    SEARCH.query = "${query}";
    SEARCH.bottom_page_html(${page},${totalPages},'');
</script>
</body>
</html>