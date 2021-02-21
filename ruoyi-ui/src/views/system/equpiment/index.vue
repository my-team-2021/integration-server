<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="设备名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入设备名称"
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
<!--      <el-form-item label="质保年份" prop="keepYears">-->
<!--        <el-input-->
<!--          v-model="queryParams.keepYears"-->
<!--          placeholder="请输入质保年份"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="设备状态 0 停用 1 正常 2报废 " prop="status">-->
<!--        <el-select v-model="queryParams.status" placeholder="请选择设备状态 0 停用 1 正常 2报废 " clearable size="small">-->
<!--          <el-option label="请选择字典生成" value="" />-->
<!--        </el-select>-->
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
          v-hasPermi="['system:equpiment:add']"
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
          v-hasPermi="['system:equpiment:edit']"
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
          v-hasPermi="['system:equpiment:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:equpiment:export']"
        >导出</el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--            type="warning"-->
<!--            plain-->
<!--            icon="el-icon-download"-->
<!--            size="mini"-->
<!--            @click="handleQrCodExport"-->
<!--            v-hasPermi="['system:equpiment:qrCodeExport']"-->
<!--        >设备二维码导出</el-button>-->
<!--      </el-col>-->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="equpimentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="设备名称" align="center" prop="name" />
      <el-table-column label="设备详情(参数信息)" align="center" prop="description" />
      <el-table-column label="设备图片" align="center" prop="picture" />
      <el-table-column label="设备编号" align="center" prop="inputCode" />
      <el-table-column label="质保年份" align="center" prop="keepYears" />
      <el-table-column label="设备状态" align="center" prop="status" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:equpiment:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:equpiment:remove']"
          >删除</el-button>
          <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleQrCodeInfo(scope.row)"
              v-hasPermi="['system:equpiment:edit']"
          >查看二维码</el-button>
          <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleQrCodeExport(scope.row)"
              v-hasPermi="['system:equpiment:edit']"
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

    <!-- 添加或修改设备信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="设备名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入设备名称" />
        </el-form-item>
        <el-form-item label="设备详情(参数信息)" prop="description">
          <el-input v-model="form.description" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="设备图片" prop="picture">
          <el-input v-model="form.picture" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="设备编号" prop="inputCode">
          <el-input v-model="form.inputCode" placeholder="请输入设备编号" />
        </el-form-item>
        <el-form-item label="质保年份" prop="keepYears">
          <el-input v-model="form.keepYears" placeholder="请输入质保年份" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 查看设备二维码信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="qrCodeInfo" :model="qrCodeInfo" label-width="80px" :disabled="true">
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
import { listEqupiment, getEqupiment, delEqupiment, addEqupiment, updateEqupiment, getEqupimentQrCode } from "@/api/system/equpiment";

export default {
  name: "Equpiment",
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
      // 设备信息表格数据
      equpimentList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        description: null,
        picture: null,
        inputCode: null,
        keepYears: null,
        status: null,
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
        name: [
          { required: true, message: "设备名称不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询设备信息列表 */
    getList() {
      this.loading = true;
      listEqupiment(this.queryParams).then(response => {
        this.equpimentList = response.rows;
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
        name: null,
        description: null,
        picture: null,
        inputCode: null,
        keepYears: null,
        status: 0,
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
      this.title = "添加设备信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getEqupiment(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改设备信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateEqupiment(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addEqupiment(this.form).then(response => {
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
      this.$confirm('是否确认删除设备信息编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delEqupiment(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/equpiment/export', {
        ...this.queryParams
      }, `system_equpiment.xlsx`)
    },
    /** 查看设备二维码按钮操作 */
    handleQrCodeInfo(row) {
      this.reset();
      const id = row.id || this.ids
      getEqupimentQrCode(id).then(response => {
        this.qrCodeInfo = response.data;
        this.open = true;
        this.title = "设备二维码信息";
      });
    },
    /** 导出设备二维码按钮操作 */
    handleQrCodeExport(row) {
      this.reset();
      const id = row.id || this.ids
      getEqupimentQrCode(id).then(response => {
        let link = document.createElement('a')
        link.href = response.data.picture
        link.download = response.data.name + '.png'
        link.click()
      });
    },
    // /** 导出设备二维码按钮操作 */
    // handleQrCodExport() {
    //   // this.getUrlBase64('https://cdn.jsdelivr.net/gh/looly/hutool-site/docs/extra/images/qrcodeCustom.jpg').then(base64 => {
    //   //   let link = document.createElement('a')
    //   //   link.href = base64
    //   //   link.download = 'qrCode.png'
    //   //   link.click()
    //   // })
    //     let link = document.createElement('a')
    //     link.href = 'data:image/png;base64,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'
    //     link.download = 'qrCode.png'
    //     link.click()
    // },
    getUrlBase64(url) {
      return new Promise(resolve => {
        let canvas = document.createElement('canvas')
        let ctx = canvas.getContext('2d')
        let img = new Image()
        img.crossOrigin = 'Anonymous' //允许跨域
        img.src = url
        img.onload = function() {
          canvas.height = 300
          canvas.width = 300
          ctx.drawImage(img, 0, 0, 300, 300)
          let dataURL = canvas.toDataURL('image/png')
          canvas = null
          resolve(dataURL)
        }
      })
    }

  }
};
</script>
