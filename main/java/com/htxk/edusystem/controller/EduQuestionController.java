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
    com.htxk.edusystem.domain.EduQuestion;
    com.htxk.edusystem.service.IEduQuestionService;

/**
 * 题库Controller
 *
 * @author maple
 * @date 2021-03-18
 */
@Controller
@RequestMapping("/edusystem/question")
public class EduQuestionController extends BaseController {
    private String prefix = "edusystem/question";

    @Autowired
    private IEduQuestionService eduQuestionService;

    @RequiresPermissions("edusystem:question:view")
    @GetMapping()
    public Stringquestion() {
        return prefix + "/question";
    }

            /**
         * 查询题库列表
         */
        @RequiresPermissions("edusystem:question:list")
        @PostMapping("/list")
        @ResponseBody
        public TableDataInfo list(EduQuestion eduQuestion) {
            startPage();
            List<EduQuestion> list = eduQuestionService.selectEduQuestionList(eduQuestion);
            return getDataTable(list);
        }
    
    /**
     * 导出题库列表
     */
    @RequiresPermissions("edusystem:question:export")
    @Log(title = "题库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(EduQuestion eduQuestion) {
        List<EduQuestion> list = eduQuestionService.selectEduQuestionList(eduQuestion);
        ExcelUtil<EduQuestion> util = new ExcelUtil<EduQuestion>(EduQuestion. class);
        return util.exportExcel(list, "question");
    }

            /**
         * 新增题库
         */
        @GetMapping("/add")
        public String add() {
            return prefix + "/add";
        }
    
    /**
     * 新增保存题库
     */
    @RequiresPermissions("edusystem:question:add")
    @Log(title = "题库", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(EduQuestion eduQuestion) {
        return toAjax(eduQuestionService.insertEduQuestion(eduQuestion));
    }

    /**
     * 修改题库
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        EduQuestion eduQuestion =eduQuestionService.selectEduQuestionById(id);
        mmap.put("eduQuestion", eduQuestion);
        return prefix + "/edit";
    }

    /**
     * 修改保存题库
     */
    @RequiresPermissions("edusystem:question:edit")
    @Log(title = "题库", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(EduQuestion eduQuestion) {
        return toAjax(eduQuestionService.updateEduQuestion(eduQuestion));
    }

            /**
         * 删除题库
         */
        @RequiresPermissions("edusystem:question:remove")
        @Log(title = "题库", businessType = BusinessType.DELETE)
        @PostMapping("/remove")
        @ResponseBody
        public AjaxResult remove(String ids) {
            return toAjax(eduQuestionService.deleteEduQuestionByIds(ids));
        }
        }
