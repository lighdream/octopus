package import com.htxk.ruoyi.common.annotation.Excel;

com.htxk.edusystem.domain;

/**
 * 题库对象 edu_question
 *
 * @author maple
 * @date 2021-03-18
 */
public class EduQuestion extends BaseEntity
        {
private static final long serialVersionUID=1L;

        /** 题目序号 */
            private Long id;

            /** 题目类型 */
                                                        @Excel(name = "题目类型")
                        private String type;

            /** 题目标题 */
                                                        @Excel(name = "题目标题")
                        private String title;

            /** 题目选项 */
                                                        @Excel(name = "题目选项")
                        private String sel;

            /** 题目分值 */
                                                        @Excel(name = "题目分值")
                        private Double score;

            /** 题目答案/关键词 */
                                                        @Excel(name = "题目答案/关键词")
                        private String answer;

                            /** 删除标志（0代表存在 2代表删除） */
            private String delFlag;

                            public void setId(Long id)
            {
            this.id = id;
            }

    public Long getId()
            {
            return id;
            }
                            public void setType(String type)
            {
            this.type = type;
            }

    public String getType()
            {
            return type;
            }
                            public void setTitle(String title)
            {
            this.title = title;
            }

    public String getTitle()
            {
            return title;
            }
                            public void setSel(String sel)
            {
            this.sel = sel;
            }

    public String getSel()
            {
            return sel;
            }
                            public void setScore(Double score)
            {
            this.score = score;
            }

    public Double getScore()
            {
            return score;
            }
                            public void setAnswer(String answer)
            {
            this.answer = answer;
            }

    public String getAnswer()
            {
            return answer;
            }
                                            public void setDelFlag(String delFlag)
            {
            this.delFlag = delFlag;
            }

    public String getDelFlag()
            {
            return delFlag;
            }
    
@Override
public String toString(){
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                                .append("id",getId())
                                .append("type",getType())
                                .append("title",getTitle())
                                .append("sel",getSel())
                                .append("score",getScore())
                                .append("answer",getAnswer())
                                .append("createBy",getCreateBy())
                                .append("createTime",getCreateTime())
                                .append("updateBy",getUpdateBy())
                                .append("updateTime",getUpdateTime())
                                .append("delFlag",getDelFlag())
            .toString();
        }
        }
