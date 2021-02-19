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
      <el-form-item label="设备名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入设备名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="设备状态 0 停用 1 正常 2报废 " prop="status">-->
<!--        <el-select v-model="queryParams.status" placeholder="请选择设备状态 0 停用 1 正常 2报废 " clearable size="small">-->
<!--          <el-option label="请选择字典生成" value="" />-->
<!--        </el-select>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="巡检周期" prop="period">-->
<!--        <el-input-->
<!--          v-model="queryParams.period"-->
<!--          placeholder="请输入巡检周期"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="最近检修时间" prop="lastCheckTime">-->
<!--        <el-date-picker clearable size="small"-->
<!--          v-model="queryParams.lastCheckTime"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="选择最近检修时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="下次检修时间" prop="nextCheckTime">-->
<!--        <el-date-picker clearable size="small"-->
<!--          v-model="queryParams.nextCheckTime"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="选择下次检修时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="创建人名称" prop="createByName">-->
<!--        <el-input-->
<!--          v-model="queryParams.createByName"-->
<!--          placeholder="请输入创建人名称"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="最后修改人名称" prop="updateByName">-->
<!--        <el-input-->
<!--          v-model="queryParams.updateByName"-->
<!--          placeholder="请输入最后修改人名称"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="逻辑删除标记" prop="enabled">-->
<!--        <el-input-->
<!--          v-model="queryParams.enabled"-->
<!--          placeholder="请输入逻辑删除标记"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="版本字段" prop="version">-->
<!--        <el-input-->
<!--          v-model="queryParams.version"-->
<!--          placeholder="请输入版本字段"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
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
          v-hasPermi="['system:device:add']"
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
          v-hasPermi="['system:device:edit']"
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
          v-hasPermi="['system:device:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:device:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="deviceList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="主键" align="center" prop="id" />-->
      <el-table-column label="厂房名称" align="center" prop="factoryId" />
      <el-table-column label="房间编号" align="center" prop="roomId" />
      <el-table-column label="设备编号" align="center" prop="inputCode" />
      <el-table-column label="设备名称" align="center" prop="name" />
<!--      <el-table-column label="设备详情(参数信息)" align="center" prop="description" />-->
<!--      <el-table-column label="设备图片" align="center" prop="picture" />-->
<!--      <el-table-column label="设备状态 0 停用 1 正常 2报废 " align="center" prop="status" />-->
      <el-table-column label="巡检周期" align="center" prop="period" />
      <el-table-column label="最近检修时间" align="center" prop="lastCheckTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.lastCheckTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="下次检修时间" align="center" prop="nextCheckTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.nextCheckTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
<!--      <el-table-column label="创建人名称" align="center" prop="createByName" />-->
<!--      <el-table-column label="最后修改人名称" align="center" prop="updateByName" />-->
<!--      <el-table-column label="逻辑删除标记" align="center" prop="enabled" />-->
<!--      <el-table-column label="版本字段" align="center" prop="version" />-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:device:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:device:remove']"
          >删除</el-button>
          <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleQrCodeInfo(scope.row)"
              v-hasPermi="['system:device:edit']"
          >查看二维码</el-button>
          <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleQrCodeExport(scope.row)"
              v-hasPermi="['system:device:edit']"
          >导出二维码</el-button>
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

    <!-- 添加或修改设备信息维护对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="厂房名称" prop="factoryId">
          <el-input v-model="form.factoryId" placeholder="请输入厂房名称" />
        </el-form-item>
        <el-form-item label="房间编号" prop="roomId">
          <el-input v-model="form.roomId" placeholder="请输入房间编号" />
        </el-form-item>
        <el-form-item label="设备编号" prop="inputCode">
          <el-input v-model="form.inputCode" placeholder="请输入设备编号" />
        </el-form-item>
        <el-form-item label="设备名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入设备名称" />
        </el-form-item>
<!--        <el-form-item label="设备详情(参数信息)" prop="description">-->
<!--          <el-input v-model="form.description" type="textarea" placeholder="请输入内容" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="设备图片" prop="picture">-->
<!--          <el-input v-model="form.picture" type="textarea" placeholder="请输入内容" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="设备状态 0 停用 1 正常 2报废 ">-->
<!--          <el-radio-group v-model="form.status">-->
<!--            <el-radio label="1">请选择字典生成</el-radio>-->
<!--          </el-radio-group>-->
<!--        </el-form-item>-->
        <el-form-item label="巡检周期" prop="period">
          <el-input v-model="form.period" placeholder="请输入巡检周期" />
        </el-form-item>
        <el-form-item label="最近检修时间" prop="lastCheckTime">
          <el-date-picker clearable size="small"
            v-model="form.lastCheckTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择最近检修时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="下次检修时间" prop="nextCheckTime">
          <el-date-picker clearable size="small"
            v-model="form.nextCheckTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择下次检修时间">
          </el-date-picker>
        </el-form-item>
<!--        <el-form-item label="创建人名称" prop="createByName">-->
<!--          <el-input v-model="form.createByName" placeholder="请输入创建人名称" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="最后修改人名称" prop="updateByName">-->
<!--          <el-input v-model="form.updateByName" placeholder="请输入最后修改人名称" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="逻辑删除标记" prop="enabled">-->
<!--          <el-input v-model="form.enabled" placeholder="请输入逻辑删除标记" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="版本字段" prop="version">-->
<!--          <el-input v-model="form.version" placeholder="请输入版本字段" />-->
<!--        </el-form-item>-->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 查看设备二维码信息对话框 -->
    <el-dialog :title="qrCodeTitle" :visible.sync="qrCodeOpen" width="500px" append-to-body>
      <el-form ref="qrCodeInfo" :model="qrCodeInfo" label-width="80px">
        <el-form-item label="设备名称" prop="name">
          <el-input v-model="qrCodeInfo.name" placeholder="请输入设备名称" />
        </el-form-item>
        <el-form-item label="设备图片" prop="picture">
          <img :src="qrCodeInfo.picture" class="login-code-img"/>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { listDevice, getDevice, delDevice, addDevice, updateDevice, getDeviceQrCode} from "@/api/system/device";
import { getEqupimentQrCode } from '@/api/system/equpiment'

export default {
  name: "Device",
  components: {
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 设备信息维护表格数据
      deviceList: [],
      // 弹出层标题
      title: "",
      // 弹出层标题
      qrCodeTitle: "",
      // 是否显示弹出层
      open: false,
      // 是否显示弹出层
      qrCodeOpen: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        factoryId: null,
        roomId: null,
        inputCode: null,
        name: null,
        description: null,
        picture: null,
        status: null,
        period: null,
        lastCheckTime: null,
        nextCheckTime: null,
        createByName: null,
        updateByName: null,
        enabled: null,
        version: null
      },
      // 表单参数
      form: {},
      // 设备二维码信息
      qrCodeInfo: {},
      // 表单校验
      rules: {
        inputCode: [
          { required: true, message: "设备编号不能为空", trigger: "blur" }
        ],
        name: [
          { required: true, message: "设备名称不能为空", trigger: "blur" }
        ],
        period: [
          { required: true, message: "巡检周期不能为空", trigger: "blur" }
        ],
        nextCheckTime: [
          { required: true, message: "下次检修时间不能为空", trigger: "blur" }
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
    /** 查询设备信息维护列表 */
    getList() {
      this.loading = true;
      listDevice(this.queryParams).then(response => {
        this.deviceList = response.rows;
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
        name: null,
        description: null,
        picture: null,
        status: 0,
        period: null,
        lastCheckTime: null,
        nextCheckTime: null,
        createBy: null,
        createByName: null,
        createTime: null,
        updateBy: null,
        updateByName: null,
        updateTime: null,
        enabled: null,
        version: null
      };
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
      this.title = "添加设备信息维护";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getDevice(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改设备信息维护";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateDevice(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDevice(this.form).then(response => {
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
      this.$confirm('是否确认删除设备信息维护编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delDevice(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/device/export', {
        ...this.queryParams
      }, `system_device.xlsx`)
    },
    /** 查看设备二维码按钮操作 */
    handleQrCodeInfo(row) {
      this.reset();
      const id = row.id || this.ids
      getDeviceQrCode(id).then(response => {
        this.qrCodeInfo = response.data;
        this.qrCodeOpen = true;
        this.qrCodeTitle = "设备二维码信息";
      });
    },
    /** 导出设备二维码按钮操作 */
    handleQrCodeExport(row) {
      this.reset();
      const id = row.id || this.ids
      getDeviceQrCode(id).then(response => {
        let link = document.createElement('a')
        link.href = response.data.picture
        link.download = response.data.name + '.png'
        link.click()
      });
    }
  }
};
</script>
