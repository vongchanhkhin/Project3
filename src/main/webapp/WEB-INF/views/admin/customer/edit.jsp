<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="customerAPI" value="/api/customer"/>
<c:url var="transactionAPI" value="/api/transaction"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <c:if test="${empty customerEdit.id}">
        <title>Thêm khách hàng</title>
    </c:if>
    <c:if test="${not empty customerEdit.id}">
        <title>Thông tin khách hàng</title>
    </c:if>
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
                    <a href="/admin/customer-list">Trang chủ</a>
                </li>
                <c:if test="${empty customerEdit.id}">
                    <li class="active">Thêm khách hàng</li>
                </c:if>
                <c:if test="${not empty customerEdit.id}">
                    <li class="active">Thông tin khách hàng</li>
                </c:if>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content" style="font-family: 'Times New Roman', Times, serif;">

            <div class="page-header">
                <c:if test="${empty customerEdit.id}">
                    <h1>Thêm khách hàng</h1>
                </c:if>
                <c:if test="${not empty customerEdit.id}">
                    <h1>Thông tin khách hàng</h1>
                </c:if>
            </div><!-- /.page-header -->

            <div class="row">
                <div class="col-xs-12">
                    <form:form modelAttribute="customerEdit" method="GET" cssClass="form-horizontal" id="form-edit">
                        <div class="form-group">
                            <label class="col-xs-3">Tên khách hàng</label>
                            <div class="col-xs-9">
                                <form:input path="name" cssClass="form-control" required="required"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Số điện thoại</label>
                            <div class="col-xs-9">
                                <form:input path="customerPhone" cssClass="form-control" required="required"/>
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
                            <div class="col-xs-3">
                                <form:select path="status" cssClass="form-control">
                                    <form:option value="">--- Chọn tình trạng ---</form:option>
                                    <form:options items="${statusTypes}"/>
                                </form:select>
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

            <c:forEach var="item" items="${transactionTypes}">
                <div class="col-xs-12">
                    <div class="col-sm-12">
                        <h3 class="header smaller lighter blue">${item.value}</h3>
                        <button class="btn btn-lg btn-primary"
                                onclick="transactionType('${item.key}')">
                            <i class="orange ace-icon fa fa-location-arrow bigger-130"></i>Add
                        </button>
                    </div>
                    <c:if test="${item.key == 'CSKH'}">
                        <div class="col-xs-12">
                            <table id="table-CSKH" class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>Ngày tạo</th>
                                    <th>Người tạo</th>
                                    <th>Ngày sửa</th>
                                    <th>Người sửa</th>
                                    <th>Chi tiết giao dịch</th>
                                    <th>Thao tác</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="transaction" items="${transactions}">
                                    <c:if test="${transaction.code == 'CSKH'}">
                                        <tr>
                                            <td>${transaction.createdDate}</td>
                                            <td>${transaction.createdBy}</td>
                                            <td>${transaction.modifiedDate}</td>
                                            <td>${transaction.modifiedBy}</td>
                                            <td>${transaction.note}</td>
                                            <td>
                                                <button class="btn btn-xs btn-info" title="Sửa thông tin"
                                                        onclick="updateTransaction(${transaction.id}, '${transaction.code}')">
                                                    <i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
                                                </button>
                                            </td>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:if>
                    <c:if test="${item.key == 'DDX'}">
                        <div class="col-xs-12">
                            <table id="table-DDX" class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>Ngày tạo</th>
                                    <th>Người tạo</th>
                                    <th>Ngày sửa</th>
                                    <th>Người sửa</th>
                                    <th>Chi tiết giao dịch</th>
                                    <th>Thao tác</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="transaction" items="${transactions}">
                                    <c:if test="${transaction.code == 'DDX'}">
                                        <tr>
                                            <td>${transaction.createdDate}</td>
                                            <td>${transaction.createdBy}</td>
                                            <td>${transaction.modifiedDate}</td>
                                            <td>${transaction.modifiedBy}</td>
                                            <td>${transaction.note}</td>
                                            <td>
                                                <button class="btn btn-xs btn-info" title="Sửa thông tin"
                                                        onclick="updateTransaction(${transaction.id}, '${transaction.code}')">
                                                    <i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
                                                </button>
                                            </td>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:if>
                </div>
            </c:forEach>
        </div><!-- /.page-content -->
    </div>
</div>
<div class="modal fade" id="transactionTypeModal" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Nhập giao dịch</h4>
            </div>
            <div class="modal-body">
                <div class="form-group has-success">
                    <label for="note" class="col-xs-12 col-sm-3 col-label no-padding-right">
                        Chi tiết giao dịch
                    </label>
                    <div class="col-xs-12 col-sm-9" id="noteCheck">
                        <span class="block input-icon input-icon-right">
                            <input type="text" class="form-control" id="note" name="note" required="required"/>
                        </span>
                    </div>
                </div>
                <input type="hidden" name="customerId" id="customerId" value="">
                <input type="hidden" name="code" id="code" value="">
                <input type="hidden" name="id" id="id" value="">
<%--                <input type="hidden" id="isAddTransaction" value="">--%>
            </div>
            <div class="modal-footer">
<%--                <c:if test="${empty isAddTransaction}">--%>
<%--                    <button type="button" class="btn btn-info" id="btnAddOrUpdateTransaction">Cập nhật</button>--%>
<%--                </c:if>--%>
<%--                <c:if test="${not empty isAddTransaction}">--%>
<%--                    <button type="button" class="btn btn-info" id="btnAddOrUpdateTransaction">Thêm giao dịch</button>--%>
<%--                </c:if>--%>
                <button type="button" class="btn btn-info" id="btnAddOrUpdateTransaction">Cập nhật</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
</div>
<script>
    function transactionType(code) {
        $('#transactionTypeModal').modal();
        // $('#customerId').val(customerId);
        $('#code').val(code);
        // $('#isAddTransaction').val('true'); //marked
    }

    function updateTransaction(id, code) {
        $('#transactionTypeModal').modal();
        $('#id').val(id);
        $('#code').val(code);
        // $('#isAddTransaction').val(''); //marked
        getNoteByTransactionId(id);
    }

    function getNoteByTransactionId(transactionId) {
        $.ajax({
            type: "GET",
            url: "${transactionAPI}/" + transactionId + '/note',
            dataType: "json",
            success: function (res) {
                var note = res.note;
                $('#note').val(note);
                console.info("success");
            },
            error: function (res) {
                console.log("Fail!")
            }
        });
    }

    function executingAddOrUpdateTransaction(data) {
        $.ajax({
            type: "POST",
            url: "${transactionAPI}",
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                console.log("success");
                if (res.id != null) {
                    window.location.reload();
                    alert("Update Transaction Success");
                } else {
                    window.location.reload();
                    alert("Add Transaction Success");
                }
            },
            error: function (res) {
                console.log(res);
                window.location.href = '<c:url value="/admin/customer-edit?message=error_system"/>';
            }
        });
    }

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

    $('#btnAddOrUpdateTransaction').click(function (e) {
        e.preventDefault();
        var data = {};
        data['id'] = $('#id').val();
        data['customerId'] = $('#customerId').val();
        data['code'] = $('#code').val();
        data['note'] = $('#note').val();

        if (data['note'] != '') {
            executingAddOrUpdateTransaction(data);
        } else alert("Vui lòng nhập chi tiết giao dịch!");
    });

    $('#btnAddOrUpdateCustomer').click(function () {
        var data = {};
        var formData = $('#form-edit').serializeArray();
        $.each(formData, function (i, v) {
            data["" + v.name + ""] = v.value;
        });


        if (data['name'] != '' && data['customerPhone'] != '') {
            executingAddOrUpdateCustomer(data);
        } else {
            alert("Vui lòng nhập đầy đủ thông tin!");
            <%--window.location.href = '<c:url value="/admin/customer-edit?name=require&customerPhone=require"/>';--%>
        }
    });

    $('#btnCancel').click(function () {
        window.location.href = "/admin/customer-list";
    });


</script>
</body>
</html>
