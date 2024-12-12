<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingAPI" value="/api/building"/>
<c:url var="buildingListURL" value="/admin/building-list"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Danh sách toà nhà</title>
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
                <li class="active">Danh sách toà nhà</li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">

            <div class="page-header">
                <h1>
                    Danh sách tòa nhà
                </h1>
            </div><!-- /.page-header -->

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
                                <form:form id="listForm" modelAttribute="modelSearchRequest" action="${buildingListURL}"
                                           method="GET">
                                    <div class="row">
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-6">
                                                    <label>Tên tòa nhà</label>
                                                        <%--                                                    <input type="text" class="form-control" name="name"--%>
                                                        <%--                                                           value="${modelSearchRequest.name}">--%>
                                                    <form:input path="name" cssClass="form-control"/>
                                                </div>
                                                <div class="col-xs-6">
                                                    <label>Diện tích sàn</label>
                                                        <%--                                                    <input type="number" min="0" class="form-control"--%>
                                                        <%--                                                           name="floorArea" value="${modelSearchRequest.floorArea}">--%>
                                                    <form:input path="floorArea" cssClass="form-control"/>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-2">
                                                    <label>Quận</label>
                                                    <form:select path="district" class="form-control">
                                                        <form:option value="">---Chọn Quận---</form:option>
                                                        <form:options items="${districts}"/>
                                                    </form:select>
                                                </div>
                                                <div class="col-xs-5">
                                                    <label>Phường</label>
                                                        <%--                                                    <input type="text" min="0" class="form-control" name="ward"--%>
                                                        <%--                                                           value="${modelSearchRequest.ward}">--%>
                                                    <form:input path="ward" cssClass="form-control"/>
                                                </div>
                                                <div class="col-xs-5">
                                                    <label>Đường</label>
                                                        <%--                                                    <input type="text" class="form-control" name="street"--%>
                                                        <%--                                                           value="${modelSearchRequest.street}">--%>
                                                    <form:input path="street" cssClass="form-control"/>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-4">
                                                    <label>Số tầng hầm</label>
                                                        <%--                                                    <input type="text" class="form-control" name="numberOfBasement"--%>
                                                        <%--                                                           value="${modelSearchRequest.numberOfBasement}">--%>
                                                    <form:input path="numberOfBasement" cssClass="form-control"/>
                                                </div>
                                                <div class="col-xs-4">
                                                    <label>Hướng</label>
                                                        <%--                                                    <input type="text" class="form-control" name="direction"--%>
                                                        <%--                                                           value="${modelSearchRequest.direction}">--%>
                                                    <form:input path="direction" cssClass="form-control"/>
                                                </div>
                                                <div class="col-xs-4">
                                                    <label>Hạng</label>
                                                        <%--                                                    <input type="number" min="0" class="form-control" name="level"--%>
                                                        <%--                                                           value="">--%>
                                                    <form:input path="level" cssClass="form-control"/>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-3">
                                                    <label>Diện tích từ</label>
                                                        <%--                                                    <input type="number" min="0" class="form-control"--%>
                                                        <%--                                                           name="areaFrom" value="">--%>
                                                    <form:input path="areaFrom" cssClass="form-control"/>
                                                </div>
                                                <div class="col-xs-3">
                                                    <label>Diện tích đến</label>
                                                        <%--                                                    <input type="number" min="0" class="form-control" name="areaTo"--%>
                                                        <%--                                                           value="">--%>
                                                    <form:input path="areaTo" cssClass="form-control"/>
                                                </div>
                                                <div class="col-xs-3">
                                                    <label>Giá thuê từ</label>
                                                        <%--                                                    <input type="number" min="0" class="form-control"--%>
                                                        <%--                                                           name="rentPriceFrom" value="">--%>
                                                    <form:input path="rentPriceFrom" cssClass="form-control"/>
                                                </div>
                                                <div class="col-xs-3">
                                                    <label>Giá thuê đến</label>
                                                        <%--                                                    <input type="number" min="0" class="form-control"--%>
                                                        <%--                                                           name="rentPriceTo" value="">--%>
                                                    <form:input path="rentPriceTo" cssClass="form-control"/>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-5">
                                                    <label>Tên quản lý</label>
                                                        <%--                                                    <input type="text" class="form-control" name="managerName" value="">--%>
                                                    <form:input path="managerName" cssClass="form-control"/>
                                                </div>
                                                <div class="col-xs-5">
                                                    <label>SĐT quản lý</label>
                                                        <%--                                                    <input type="text" class="form-control"--%>
                                                        <%--                                                           name="managerPhoneNumber" value="">--%>
                                                    <form:input path="managerPhone" cssClass="form-control"/>
                                                </div>
                                                <div class="col-xs-2">
                                                    <label>Nhân viên</label>
                                                    <form:select path="staffId" class="form-control">
                                                        <form:option value="">Chọn Nhân Viên</form:option>
                                                        <form:options items="${staffList}"/>
                                                    </form:select>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-6">
                                                    <form:checkboxes path="typeCode" items="${typeCodes}"/>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-6">
                                                    <button type="button" class="btn btn-danger"
                                                            id="btnSearchBuilding">
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
                            <a href="/admin/building-edit">
                                <button class="btn btn-info" title="Thêm tòa nhà">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                         class="bi bi-building-add"
                                         viewBox="0 0 16 16">
                                        <path
                                                d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0"/>
                                        <path
                                                d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"/>
                                        <path
                                                d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                                    </svg>
                                </button>
                            </a>
                            <button class="btn btn-danger" title="Xóa tòa nhà" id="btnDeleteBuilding">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                     class="bi bi-building-dash"
                                     viewBox="0 0 16 16">
                                    <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7M11 12h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1 0-1"/>
                                    <path
                                            d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"/>
                                    <path
                                            d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                                </svg>
                            </button>
                        </div>
                    </div>
                </div>
            </div>

            <!-- bảng dánh sách -->
            <div class="row">
                <div class="col-xs-12">
                    <table id="buildingResultListTable" class="table table-striped table-bordered table-hover"
                           style="margin: 3em 0 1.5em;">
                        <thead>
                        <tr>
                            <th class="center">
                                <label class="pos-rel">
                                    <input type="checkbox" class="ace">
                                    <span class="lbl"></span>
                                </label>
                            </th>
                            <th>Tên tòa nhà</th>
                            <th>Địa chỉ</th>
                            <th>Số tầng hầm</th>
                            <th>Giá thuê</th>
                            <th>Tên quản lý</th>
                            <th>Số điện thoại</th>
                            <th>Diện tích sàn</th>
                            <th>Diện tích trống</th>
                            <th>Diện tích thuê</th>
                            <th>Phí dịch vụ</th>
                            <th>Phí môi giới</th>
                            <th style="width: 100px;">Thao tác</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="item" items="${buildingResultList}">
                            <tr>
                                <td class="center">
                                    <label class="pos-rel">
                                        <input type="checkbox" name="checkList" class="check-box-element"
                                               value="${item.id}" id="checkbox_${item.id}">
                                        <span class="lbl"></span>
                                    </label>
                                </td>
                                <td>${item.name}</td>
                                <td>${item.address}</td>
                                <td>${item.numberOfBasement}</td>
                                <td>${item.rentPrice}</td>
                                <td>${item.managerName}</td>
                                <td>${item.managerPhone}</td>
                                <td>${item.rentArea}</td>
                                <td>${item.emptyArea}</td>
                                <td>${item.rentArea}</td>
                                <td>${item.serviceFee}</td>
                                <td>${item.brokerageFee}</td>
                                <td>
                                    <div class="hidden-sm hidden-xs btn-group">
                                        <button class="btn btn-xs btn-success" title="Giao toà nhà"
                                                onclick="assignmentBuilding(${item.id})">
                                            <i class="ace-icon glyphicon glyphicon-list"></i>
                                        </button>

                                        <a class="btn btn-xs btn-info" href="/admin/building-edit-${item.id}"
                                           title="Sửa toà nhà">
                                            <i class="ace-icon fa fa-pencil bigger-120"></i>
                                        </a>

                                        <button class="btn btn-xs btn-danger" title="Xoá toà nhà" onclick="deleteBuilding(${item.id})">
                                            <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                        </button>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div><!-- /.span -->
            </div>
        </div><!-- /.page-content -->
    </div>
</div><!-- /.main-content -->
<div class="modal fade" id="assignmentBuildingModal" role="dialog">
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
                <input type="hidden" name="building" id="buildingId" value="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-info" id="btnAssignmentBuilding">Giao toà nhà</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
</div>

<script>
    function assignmentBuilding(buidlingId) {
        $('#assignmentBuildingModal').modal();
        $('#buildingId').val(buidlingId);
        loadStaff(buidlingId);
    }

    function loadStaff(buildingId) {
        $.ajax({
            type: "GET",
            url: "${buildingAPI}/" + buildingId + "/staffs",
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
                window.location.href = "<c:url value="/admin/building-list?message=error"/>";
            }
        });
    }

    $('#btnAssignmentBuilding').click(function (e) {
        e.preventDefault();
        var data = {};
        data['buildingId'] = $('#buildingId').val();
        var staffs = $('#staffList').find('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        data['staffs'] = staffs;
        if(staffs != '') {
            executingAssignmentBuilding(data);
        }
    });

    function executingAssignmentBuilding(data) {
        $.ajax({
            type: "POST",
            url: "${buildingAPI}" + "/assignment",
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                console.info("success");
            },
            error: function (res) {
                console.info("Giao toà nhà không thành công!");
                console.log(res);
                window.location.href = "<c:url value="/admin/building-list?message=error"/>";
            }
        });
    }

    $('#btnSearchBuilding').click(function (e) {
        e.preventDefault();
        $('#listForm').submit();
    });

    // xoá 1 building
    function deleteBuilding(id) {
        var buildingId = [id];
        executingDeleteBuilding(buildingId);
    }

    // xoá nhiều building
    $('#btnDeleteBuilding').click(function (e) {
        e.preventDefault();
        var buildingIds = $('#buildingResultListTable').find('tbody input[type=checkbox]:checked').map(function() {
            return $(this).val();
        }).get();
        executingDeleteBuilding(buildingIds);
    });

    function executingDeleteBuilding(data) {
        $.ajax({
            type: "DELETE",
            url: "${buildingAPI}/" + data,
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "JSON",
            success: function (res) {
                console.log("success");
            },
            error: function (res) {
                console.log(res);
            }
        });
    }
</script>
</body>
</html>
