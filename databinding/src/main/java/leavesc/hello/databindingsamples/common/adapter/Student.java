package leavesc.hello.databindingsamples.common.adapter;

/**
 * 作者：罗发新
 * 时间：2019/5/30 0030    星期四
 * 邮件：424533553@qq.com
 * 说明：
 */
public class Student {

    private int resId;

    private String name;

    public Student(int resId, String name) {
        this.resId = resId;
        this.name = name;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


