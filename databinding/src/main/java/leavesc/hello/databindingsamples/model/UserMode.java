package leavesc.hello.databindingsamples.model;

import android.databinding.ObservableField;

/**
 * 作者：leavesC
 * 时间：2019/2/27 21:36
 * 描述：
 * GitHub：https://github.com/leavesC
 * Blog：https://www.jianshu.com/u/9df45b87cfdf
 */
public class UserMode {

    private ObservableField<String> name;

    private ObservableField<String> password;

    public UserMode(String name, String password) {
        this.name = new ObservableField<>(name);
        this.password = new ObservableField<>(password);
    }

    public ObservableField<String> getName() {
        return name;
    }

    public void setName(ObservableField<String> name) {
        this.name = name;
    }

    public ObservableField<String> getPassword() {
        return password;
    }

    public void setPassword(ObservableField<String> password) {
        this.password = password;
    }
}
