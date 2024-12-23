<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="customerAPI" value="/api/customer"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Chỉnh sửa thông tin</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check('breadcrumbs', 'fixed')
                } catch (e) {
                }
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Trang chủ</a>
                </li>
                <li class="active">Thêm khách hàng</li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content" style="font-family: 'Times New Roman', Times, serif;">

            <div class="page-header">
                <h1>
                    Thông tin khách hàng
                </h1>
            </div><!-- /.page-header -->

            <div class="row">
                <div class="col-xs-12">
                    <form:form modelAttribute="customerEdit" method="GET" cssClass="form-horizontal" id="form-edit">
                        <div class="form-group">
                            <label class="col-xs-3">Tên khách hàng</label>
                            <div class="col-xs-9">
                                <form:input path="name" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Số điện thoại</label>
                            <div class="col-xs-9">
                                <form:input path="customerPhone" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Email</label>
                            <div class="col-xs-9">
                                <form:input path="email" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Tên công ty</label>
                            <div class="col-xs-9">
                                <form:input path="companyName" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Nhu cầu</label>
                            <div class="col-xs-9">
                                <form:input path="demand" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Tình trạng</label>
                            <div class="col-xs-9">
                                <form:input path="status" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3"></label>
                            <div class="col-xs-9">
                                <c:if test="${not empty customerEdit.id}">
                                    <button type="button" class="btn btn-info" id="btnAddOrUpdateCustomer">
                                        Cập nhật thông tin
                                    </button>
                                    <button type="button" class="btn btn-danger" id="btnCancel">Huỷ thao tác</button>
                                </c:if>
                                <c:if test="${empty customerEdit.id}">
                                    <button type="button" class="btn btn-info" id="btnAddOrUpdateCustomer">
                                        Thêm khách hàng
                                    </button>
                                    <button type="button" class="btn btn-danger" id="btnCancel">Huỷ thao tác</button>
                                </c:if>
                            </div>
                        </div>
                        <form:hidden path="id" id="customerId"/>
                    </form:form>
                </div>
            </div>
        </div><!-- /.page-content -->
    </div>
</div>
<script>
    $('#btnAddOrUpdateCustomer').click(function () {
        var data = {};
        var formData = $('#form-edit').serializeArray();
        $.each(formData, function (i, v) {
                data["" + v.name + ""] = v.value;
        });


        if (data['name'] != '' && data['customerPhone'] != '') {
            executingAddOrUpdateCustomer(data);
        } else window.location.href = '<c:url value="/admin/customer-edit?customerName=require&customerPhone=require"/>';
    });

    $('#btnCancel').click(function () {
        window.location.href = "/admin/customer-list";
    });

    function executingAddOrUpdateCustomer(data) {
        $.ajax({
            type: "POST",
            url: "${customerAPI}",
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                console.log("success");
                if (res.id != null)
                    window.location.href = '<c:url value="/admin/customer-list?message=update_success"/>'
                else window.location.href = '<c:url value="/admin/customer-list?message=insert_success"/>';
            },
            error: function (res) {
                console.log(res);
                window.location.href = '<c:url value="/admin/customer-list?message=error_system"/>';
            }
        });
    }
</script>
</body>
</html>
