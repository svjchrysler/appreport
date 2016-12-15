package gob.ice.crashreportsc.interfaces;

import android.view.View;
import android.widget.Switch;

/**
 * Created by mvaldez on 13/12/2016.
 */

public interface OnClick {
        void onClick(View component, int number, String item);

        void onClickSwitch(View component);

        void onClickSwitchObject(Switch component, String name);

}
