<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="厂房名称" prop="factoryId">
        <el-input
          v-model="queryParams.factoryId"
          placeholder="请输入厂房名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="房间编号" prop="roomId">
        <el-input
          v-model="queryParams.roomId"
          placeholder="请输入房间编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备编号" prop="inputCode">
        <el-input
          v-model="queryParams.inputCode"
          placeholder="请输入设备编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择设备类型" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="检查人" prop="checkUser">
        <el-input
          v-model="queryParams.checkUser"
          placeholder="请输入检查人姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="检查时间" prop="checkTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.checkTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择检查时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="检修版本" prop="version">
        <el-input
          v-model="queryParams.version"
          placeholder="请输入检修版本"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:checkLog:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:checkLog:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:checkLog:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:checkLog:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="checkLogList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="厂房名称" align="center" prop="factoryId" />
      <el-table-column label="房间编号" align="center" prop="roomId" />
      <el-table-column label="设备编号" align="center" prop="inputCode" />
      <el-table-column label="设备类型" align="center" prop="type" />
      <el-table-column label="检查人姓名" align="center" prop="checkUser" />
      <el-table-column label="检查时间" align="center" prop="checkTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.checkTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="检修版本" align="center" prop="version" />
      <el-table-column label="创建人名称" align="center" prop="createByName" />
      <el-table-column label="最后修改人名称" align="center" prop="updateByName" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
           <el-button
             size="mini"
             type="text"
             icon="el-icon-edit"
             @click="handleLogInfo(scope.row)"
             v-hasPermi="['system:checkLog:edit']"
           >详情</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:checkLog:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:checkLog:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改检修操作记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1000px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="厂房名称" prop="factoryId">
              <el-input v-model="form.factoryId" placeholder="请输入厂房名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="房间编号" prop="roomId">
              <el-input v-model="form.roomId" placeholder="请输入房间编号" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="设备编号" prop="inputCode">
              <el-input v-model="form.inputCode" placeholder="请输入设备编号" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="设备类型" prop="type">
              <el-select v-model="form.type" placeholder="请选择设备类型">
                <el-option label="请选择字典生成" value="" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="检查人姓名" prop="checkUser">
              <el-input v-model="form.checkUser" placeholder="请输入检查人姓名" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="检查时间" prop="checkTime">
              <el-date-picker clearable size="small"
                              v-model="form.checkTime"
                              type="date"
                              value-format="yyyy-MM-dd"
                              placeholder="选择检查时间">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="检修版本" prop="version">
              <el-input v-model="form.version" placeholder="请输入检修版本" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="创建人名称" prop="createByName">
              <el-input v-model="form.createByName" placeholder="请输入创建人名称" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="最后修改人名称" prop="updateByName">
              <el-input v-model="form.updateByName" placeholder="请输入最后修改人名称" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-divider content-position="center">检修项详情信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddCheckItemDetail">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteCheckItemDetail">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="checkItemDetailList" :row-class-name="rowCheckItemDetailIndex" @selection-change="handleCheckItemDetailSelectionChange" ref="checkItemDetail">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="选中的选中集合，以逗号隔开" prop="selecteds">
            <template slot-scope="scope">
              <el-input v-model="scope.row.selecteds" placeholder="请输入选中的选中集合，以逗号隔开" />
            </template>
          </el-table-column>
          <el-table-column label="文字备注" prop="fontRemark">
            <template slot-scope="scope">
              <el-input v-model="scope.row.fontRemark" placeholder="请输入文字备注" />
            </template>
          </el-table-column>
          <el-table-column label="图片备注" prop="imgRemarks">
            <template slot-scope="scope">
              <el-input v-model="scope.row.imgRemarks" placeholder="请输入图片备注" />
            </template>
          </el-table-column>
          <el-table-column label="视频备注，一个检查项只能一个视频" prop="videoRemark">
            <template slot-scope="scope">
              <el-input v-model="scope.row.videoRemark" placeholder="请输入视频备注，一个检查项只能一个视频" />
            </template>
          </el-table-column>
          <el-table-column label="创建人名称" prop="createByName">
            <template slot-scope="scope">
              <el-input v-model="scope.row.createByName" placeholder="请输入创建人名称" />
            </template>
          </el-table-column>
          <el-table-column label="最后修改人名称" prop="updateByName">
            <template slot-scope="scope">
              <el-input v-model="scope.row.updateByName" placeholder="请输入最后修改人名称" />
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 检修操作记录详情信息 -->
    <el-dialog :title="logTitle" :visible.sync="logOpen" width="1000px" append-to-body>
      <el-form ref="logForm" :model="logForm" label-width="80px"  :disabled="true">
        <el-row>
          <el-col :span="12">
            <el-form-item label="厂房名称" prop="factoryId">
              <el-input v-model="logForm.factoryId" placeholder="请输入厂房名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="房间编号" prop="roomId">
              <el-input v-model="logForm.roomId" placeholder="请输入房间编号" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="设备编号" prop="inputCode">
              <el-input v-model="logForm.inputCode" placeholder="请输入设备编号" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="设备类型" prop="type">
              <el-select v-model="logForm.type" placeholder="请选择设备类型">
                <el-option label="请选择字典生成" value="" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="检查人姓名" prop="checkUser">
              <el-input v-model="logForm.checkUser" placeholder="请输入检查人姓名" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="检查时间" prop="checkTime">
              <el-date-picker clearable size="small"
                              v-model="logForm.checkTime"
                              type="date"
                              value-format="yyyy-MM-dd"
                              placeholder="选择检查时间">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="检修版本" prop="version">
              <el-input v-model="logForm.version" placeholder="请输入检修版本" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="创建人名称" prop="createByName">
              <el-input v-model="logForm.createByName" placeholder="请输入创建人名称" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="最后修改人名称" prop="updateByName">
              <el-input v-model="logForm.updateByName" placeholder="请输入最后修改人名称" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-divider content-position="center">检修项详情信息</el-divider>
        <el-table :data="checkItemDetailList" :row-class-name="rowCheckItemDetailIndex" @selection-change="handleCheckItemDetailSelectionChange" ref="checkItemDetail">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="选中的选中集合，以逗号隔开" prop="selecteds">
            <template slot-scope="scope">
              <el-input v-model="scope.row.selecteds" placeholder="请输入选中的选中集合，以逗号隔开" />
            </template>
          </el-table-column>
          <el-table-column label="文字备注" prop="fontRemark">
            <template slot-scope="scope">
              <el-input v-model="scope.row.fontRemark" placeholder="请输入文字备注" />
            </template>
          </el-table-column>
          <el-table-column label="图片备注" prop="imgRemarks">
            <template slot-scope="scope">
              <el-input v-model="scope.row.imgRemarks" placeholder="请输入图片备注" />
            </template>
          </el-table-column>
          <el-table-column label="视频备注，一个检查项只能一个视频" prop="videoRemark">
            <template slot-scope="scope">
              <el-input v-model="scope.row.videoRemark" placeholder="请输入视频备注，一个检查项只能一个视频" />
            </template>
          </el-table-column>
          <el-table-column label="创建人名称" prop="createByName">
            <template slot-scope="scope">
              <el-input v-model="scope.row.createByName" placeholder="请输入创建人名称" />
            </template>
          </el-table-column>
          <el-table-column label="最后修改人名称" prop="updateByName">
            <template slot-scope="scope">
              <el-input v-model="scope.row.updateByName" placeholder="请输入最后修改人名称" />
            </template>
          </el-table-column>
        </el-table>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { listCheckLog, getCheckLog, delCheckLog, addCheckLog, updateCheckLog } from "@/api/system/checkLog";

export default {
  name: "CheckLog",
  components: {
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedCheckItemDetail: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 检修操作记录表格数据
      checkLogList: [],
      // 检修项详情表格数据
      checkItemDetailList: [],
      // 弹出层标题
      title: "",
      // 弹出层标题
      logTitle: "",
      // 是否显示弹出层
      open: false,
      // 是否显示弹出层
      logOpen: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        factoryId: null,
        roomId: null,
        inputCode: null,
        type: null,
        checkUser: null,
        checkTime: null,
        version: null,
        createByName: null,
        updateByName: null,
        enabled: null
      },
      // 表单参数
      form: {},
      // 表单参数
      logForm: {},
      // 表单校验
      rules: {
        inputCode: [
          { required: true, message: "设备编号不能为空", trigger: "blur" }
        ],
        type: [
          { required: true, message: "设备类型不能为空", trigger: "change" }
        ],
        checkUser: [
          { required: true, message: "检查人姓名不能为空", trigger: "blur" }
        ],
        checkTime: [
          { required: true, message: "检查时间不能为空", trigger: "blur" }
        ],
        version: [
          { required: true, message: "检修版本不能为空", trigger: "blur" }
        ],
        createBy: [
          { required: true, message: "创建人id不能为空", trigger: "blur" }
        ],
        createByName: [
          { required: true, message: "创建人名称不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
        updateBy: [
          { required: true, message: "最后修改人Id不能为空", trigger: "blur" }
        ],
        updateByName: [
          { required: true, message: "最后修改人名称不能为空", trigger: "blur" }
        ],
        updateTime: [
          { required: true, message: "最后修改时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询检修操作记录列表 */
    getList() {
      this.loading = true;
      listCheckLog(this.queryParams).then(response => {
        this.checkLogList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        factoryId: null,
        roomId: null,
        inputCode: null,
        type: null,
        checkUser: null,
        checkTime: null,
        version: null,
        createBy: null,
        createByName: null,
        createTime: null,
        updateBy: null,
        updateByName: null,
        updateTime: null,
        enabled: null
      };
      this.checkItemDetailList = [];
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },




    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加检修操作记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCheckLog(id).then(response => {
        this.form = response.data;
        this.checkItemDetailList = response.data.checkItemDetailList;
        this.open = true;
        this.title = "修改检修操作记录";
      });
    },
    /** 详情按钮操作 */
    handleLogInfo(row) {
      this.reset();
      const id = row.id || this.ids
      getCheckLog(id).then(response => {
        this.logForm = response.data;
        this.checkItemDetailList = response.data.checkItemDetailList;
        this.logOpen = true;
        this.logTitle = "检修记录详情";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.checkItemDetailList = this.checkItemDetailList;
          if (this.form.id != null) {
            updateCheckLog(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCheckLog(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除检修操作记录编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delCheckLog(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
	/** 检修项详情序号 */
    rowCheckItemDetailIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 检修项详情添加按钮操作 */
    handleAddCheckItemDetail() {
      let obj = {};
      obj.selecteds = "";
      obj.fontRemark = "";
      obj.imgRemarks = "";
      obj.videoRemark = "";
      obj.createByName = "";
      obj.updateByName = "";
      obj.enabled = "";
      this.checkItemDetailList.push(obj);
    },
    /** 检修项详情删除按钮操作 */
    handleDeleteCheckItemDetail() {
      if (this.checkedCheckItemDetail.length == 0) {
        this.$alert("请先选择要删除的检修项详情数据", "提示", { confirmButtonText: "确定", });
      } else {
        this.checkItemDetailList.splice(this.checkedCheckItemDetail[0].index - 1, 1);
      }
    },
    /** 单选框选中数据 */
    handleCheckItemDetailSelectionChange(selection) {
      if (selection.length > 1) {
        this.$refs.checkItemDetail.clearSelection();
        this.$refs.checkItemDetail.toggleRowSelection(selection.pop());
      } else {
        this.checkedCheckItemDetail = selection;
      }
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/checkLog/export', {
        ...this.queryParams
      }, `system_checkLog.xlsx`)
    }
  }
};
</script>
