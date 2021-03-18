package

import com.htxk.ruoyi.common.annotation.Log;
import com.htxk.ruoyi.common.core.controller.BaseController;
import com.htxk.ruoyi.common.core.domain.AjaxResult;
import com.htxk.ruoyi.common.core.domain.Ztree;
import com.htxk.ruoyi.common.core.page.TableDataInfo;
import com.htxk.ruoyi.common.enums.BusinessType;
import com.htxk.ruoyi.common.utils.StringUtils;
import com.htxk.ruoyi.common.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

com.htxk.web.controller.edusystem;
    com.htxk.edusystem.domain.EduTestRecords;
    com.htxk.edusystem.service.IEduTestRecordsService;

/**
 * 考试记录Controller
 *
 * @author maple
 * @date 2021-03-18
 */
@Controller
@RequestMapping("/edusystem/records")
public class EduTestRecordsController extends BaseController {
    private String prefix = "edusystem/records";

    @Autowired
    private IEduTestRecordsService eduTestRecordsService;

    @RequiresPermissions("edusystem:records:view")
    @GetMapping()
    public Stringrecords() {
        return prefix + "/records";
    }

            /**
         * 查询考试记录列表
         */
        @RequiresPermissions("edusystem:records:list")
        @PostMapping("/list")
        @ResponseBody
        public TableDataInfo list(EduTestRecords eduTestRecords) {
            startPage();
            List<EduTestRecords> list = eduTestRecordsService.selectEduTestRecordsList(eduTestRecords);
            return getDataTable(list);
        }
    
    /**
     * 导出考试记录列表
     */
    @RequiresPermissions("edusystem:records:export")
    @Log(title = "考试记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(EduTestRecords eduTestRecords) {
        List<EduTestRecords> list = eduTestRecordsService.selectEduTestRecordsList(eduTestRecords);
        ExcelUtil<EduTestRecords> util = new ExcelUtil<EduTestRecords>(EduTestRecords. class);
        return util.exportExcel(list, "records");
    }

            /**
         * 新增考试记录
         */
        @GetMapping("/add")
        public String add() {
            return prefix + "/add";
        }
    
    /**
     * 新增保存考试记录
     */
    @RequiresPermissions("edusystem:records:add")
    @Log(title = "考试记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(EduTestRecords eduTestRecords) {
        return toAjax(eduTestRecordsService.insertEduTestRecords(eduTestRecords));
    }

    /**
     * 修改考试记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        EduTestRecords eduTestRecords =eduTestRecordsService.selectEduTestRecordsById(id);
        mmap.put("eduTestRecords", eduTestRecords);
        return prefix + "/edit";
    }

    /**
     * 修改保存考试记录
     */
    @RequiresPermissions("edusystem:records:edit")
    @Log(title = "考试记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(EduTestRecords eduTestRecords) {
        return toAjax(eduTestRecordsService.updateEduTestRecords(eduTestRecords));
    }

            /**
         * 删除考试记录
         */
        @RequiresPermissions("edusystem:records:remove")
        @Log(title = "考试记录", businessType = BusinessType.DELETE)
        @PostMapping("/remove")
        @ResponseBody
        public AjaxResult remove(String ids) {
            return toAjax(eduTestRecordsService.deleteEduTestRecordsByIds(ids));
        }
        }
