<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.javaweb.security.utils.SecurityUtils" %>
<%@ page import="com.javaweb.constant.SystemConstant" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="customerAPI" value="/api/customer"/>
<c:url var="customerListURL" value="/admin/customer-list"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Danh sách khách hàng</title>
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
                <li class="active">Danh sách khách hàng</li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">

            <div class="page-header">
                <h1>
                    Danh sách khách hàng
                </h1>
            </div>
            <!-- /.page-header -->

            <c:if test="${messageResponse!=null}">
                <div class="alert alert-block alert-${alert}">
                    <button type="button" class="close" data-dismiss="alert">
                        <i class="ace-icon fa fa-times"></i>
                    </button>
                        ${messageResponse}
                </div>
            </c:if>

            <div class="row">
                <div class="col-xs-12">
                    <div class="widget-box ui-sortable-handle">
                        <div class="widget-header">
                            <h5 class="widget-title">Tìm kiếm</h5>

                            <div class="widget-toolbar">
                                <a href="#" data-action="collapse">
                                    <i class="ace-icon fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>

                        <div class="widget-body" style="display: block;">
                            <div class="widget-main">
                                <form:form id="listForm" modelAttribute="modelSearchRequest" action="${customerListURL}"
                                           method="GET">
                                    <div class="row">
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-4">
                                                    <label>Tên khách hàng</label>
                                                    <form:input path="name" cssClass="form-control"/>
                                                </div>
                                                <div class="col-xs-4">
                                                    <label>Số điện thoại</label>
                                                    <form:input path="phone" cssClass="form-control"/>
                                                </div>
                                                <div class="col-xs-4">
                                                    <label>Email</label>
                                                    <form:input path="email" cssClass="form-control"/>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-4">
                                                    <security:authorize access="hasRole('MANAGER')">
                                                        <label>Nhân viên</label>
                                                        <form:select path="staffId" class="form-control">
                                                            <form:option value="">--- Chọn Nhân Viên ---</form:option>
                                                            <form:options items="${staffList}"/>
                                                        </form:select>
                                                    </security:authorize>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-6">
                                                    <button type="button" class="btn btn-danger"
                                                            id="btnSearchCustomer">
                                                        <svg xmlns="http://www.w3.org/2000/svg" width="16"
                                                             height="16"
                                                             fill="currentColor" class="bi bi-search"
                                                             viewBox="0 0 16 16">
                                                            <path
                                                                    d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0">
                                                            </path>
                                                        </svg>
                                                        Tìm kiếm
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form:form>
                            </div>
                        </div>

                        <div class="pull-right">
                            <a href="/admin/customer-edit">
                                <button class="btn btn-info" data-toggle="tooltip" title="Thêm khách hàng">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-add" viewBox="0 0 16 16">
  <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0m-2-6a3 3 0 1 1-6 0 3 3 0 0 1 6 0M8 7a2 2 0 1 0 0-4 2 2 0 0 0 0 4"/>
  <path d="M8.256 14a4.5 4.5 0 0 1-.229-1.004H3c.001-.246.154-.986.832-1.664C4.484 10.68 5.711 10 8 10q.39 0 .74.025c.226-.341.496-.65.804-.918Q8.844 9.002 8 9c-5 0-6 3-6 4s1 1 1 1z"/>
</svg>
                                </button>
                            </a>
                            <button class="btn btn-danger" title="Xóa khách hàng" id="btnDeleteCustomer"
                                    data-toggle="tooltip">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-dash" viewBox="0 0 16 16">
  <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7M11 12h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1 0-1m0-7a3 3 0 1 1-6 0 3 3 0 0 1 6 0M8 7a2 2 0 1 0 0-4 2 2 0 0 0 0 4"/>
  <path d="M8.256 14a4.5 4.5 0 0 1-.229-1.004H3c.001-.246.154-.986.832-1.664C4.484 10.68 5.711 10 8 10q.39 0 .74.025c.226-.341.496-.65.804-.918Q8.844 9.002 8 9c-5 0-6 3-6 4s1 1 1 1z"/>
</svg>
                            </button>
                        </div>
                    </div>
                </div>
            </div>

            <!-- bảng dánh sách -->
            <div class="row">
                <div class="col-xs-12">
                    <display:table name="customerResultList.listResult" cellspacing="0" cellpadding="0"
                                   requestURI="${customerListURL}" partialList="true" sort="external"
                                   size="${customerResultList.totalItems}" defaultsort="2" defaultorder="ascending"
                                   id="tableList" pagesize="${customerResultList.maxPageItems}"
                                   export="false"
                                   class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                   style="margin: 3em 0 1.5em;">
                        <display:column title="<fieldset class='form-group'>
                                                    <input type='checkbox' id='checkAll' class='check-box-element'>
                                                </fieldset>" class="center select-cell">
                            <fieldset>
                                <input type="checkbox" name="checkList" value="${tableList.id}"
                                       id="checkbox_${tableList.id}" class="check-box-element"/>
                            </fieldset>
                        </display:column>
                        <display:column headerClass="text-center" property="name" title="Tên khách hàng"/>
                        <display:column headerClass="text-center" property="phone" title="Số điện thoại"/>
                        <display:column headerClass="text-center" property="email" title="Email"/>
                        <display:column headerClass="text-center" property="demand" title="Nhu cầu"/>
                        <display:column headerClass="text-center" property="createdBy" title="Người thêm"/>
                        <display:column headerClass="text-center" property="createdDate" title="Ngày thêm"/>
                        <display:column headerClass="text-center" property="status" title="Tình trạng"/>
                        <display:column headerClass="col-actions text-center" class="center" title="Thao tác">
                            <div class="hidden-sm hidden-xs btn-group">
                                <security:authorize access="hasRole('MANAGER')">
                                    <button class="btn btn-xs btn-success" title="Giao khách hàng"
                                            onclick="assignmentCustomer(${tableList.id})">
                                        <i class="ace-icon glyphicon glyphicon-list"></i>
                                    </button>
                                </security:authorize>


                                <a class="btn btn-xs btn-info" href="/admin/customer-edit-${tableList.id}"
                                   title="Sửa thông tin">
                                    <i class="ace-icon fa fa-pencil bigger-120"></i>
                                </a>

                                <security:authorize access="hasRole('MANAGER')">
                                    <button class="btn btn-xs btn-danger" title="Xoá khách hàng"
                                            onclick="deleteCustomer(${tableList.id})">
                                        <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                    </button>
                                </security:authorize>
                            </div>
                        </display:column>
                    </display:table>
                </div><!-- /.span -->
            </div>
        </div><!-- /.page-content -->
    </div>
</div><!-- /.main-content -->
<div class="modal fade" id="assignmentCustomerModal" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Danh sách nhân viên</h4>
            </div>
            <div class="modal-body">
                <table id="staffList" class="table table-striped table-bordered table-hover center"
                       style="margin: 3em 0 1.5em;">
                    <thead>
                    <tr>
                        <th class="center">Chọn</th>
                        <th class="center">Tên nhân viên</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
                <input type="hidden" name="customer" id="customerId" value="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-info" id="btnAssignmentCustomer">Giao khách hàng</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
</div>

<script>
    function assignmentCustomer(customerId) {
        $('#assignmentCustomerModal').modal();
        $('#customerId').val(customerId);
        loadStaff(customerId);
    }

    function loadStaff(customerId) {
        $.ajax({
            type: "GET",
            url: "${customerAPI}/" + customerId + "/staffs",
            // data: JSON.stringify(buildingId),
            // contentType: "application/json",
            dataType: "json",
            success: function (res) {
                var row = '';
                $.each(res.data, function (index, item) {
                    row += '<tr>';
                    row += '<td><input type="checkbox" id="checkbox_' + item.staffId + '"' + ' value=' + item.staffId + ' ' + item.checked + '></td>';
                    row += '<td>' + item.fullName + '</td>';
                    row += '</tr>';
                });
                $('#staffList tbody').html(row);
                console.log("success");
            },
            error: function (res) {
                console.log("fail");
                console.log(res);
                window.location.href = "<c:url value="/admin/customer-list?message=error_system"/>";
            }
        });
    }

    function executingAssignmentCustomer(data) {
        $.ajax({
            type: "PUT",
            url: "${customerAPI}",
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                console.info("success");
                window.location.href = "<c:url value="/admin/customer-list?message=assignment_success"/>";
                alert("Giao khách hàng thành công.");
            },
            error: function (res) {
                console.info("Giao khách hàng thất bại!");
                console.log(res);
                window.location.href = "<c:url value="/admin/customer-list?message=assignment_error"/>";
                alert("Giao khách hàng thất bại!");
            }
        });
    }

    function executingDeleteCustomers(data) {
        $.ajax({
            type: "DELETE",
            url: "${customerAPI}/" + data,
            // data: JSON.stringify(data),
            // contentType: "application/json",
            dataType: "json",
            success: function (res) {
                console.log("success");
                window.location.href = '<c:url value="/admin/customer-list?message=delete_success"/>';
            },
            error: function (res) {
                console.log("fail");
                window.location.href = '<c:url value="/admin/customer-list?message=error_system"/>';
            }
        });
    }

    $('#btnAssignmentCustomer').click(function (e) {
        e.preventDefault();
        var data = {};
        data['customerId'] = $('#customerId').val();
        var staffs = $('#staffList').find('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        data['staffs'] = staffs;
        if (staffs != '') {
            executingAssignmentCustomer(data);
        }
    });


    $('#btnSearchCustomer').click(function (e) {
        e.preventDefault();
        $('#listForm').submit();
    });

    // xoá 1 customer
    function deleteCustomer(id) {
        showAlertBeforeDelete(function () {
            // e.preventDefault();
            var customerId = [id];
            executingDeleteCustomers(customerId);
        });
    }

    // xoá nhiều customer
    $('#btnDeleteCustomer').click(function (e) {
        e.preventDefault();
        showAlertBeforeDelete(function () {
            // e.preventDefault();
            var customerIds = $('#tableList').find('tbody input[type=checkbox]:checked').map(function () {
                return $(this).val();
            }).get();
            executingDeleteCustomers(customerIds);
        });
    });
</script>
</body>
</html>
