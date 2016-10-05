package com.blublabs.magicmirror.modules.calendar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.blublabs.magicmirror.R;
import com.blublabs.magicmirror.common.SwitchOnCheckedChangeListener;
import com.blublabs.magicmirror.modules.ModuleSettingsFragment;

import it.sephiroth.android.library.tooltip.Tooltip;

/**
 * Created by andrs on 28.09.2016.
 */

public class CalendarSettingsFragment extends ModuleSettingsFragment {

    enum TimeFormat{
        absolute,
        relative
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_module_settings_calendar, container, false);

        Spinner timeFormatSpinner = (Spinner) view.findViewById(R.id.spinnerTimeFormat);

        ArrayAdapter<TimeFormat> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, TimeFormat.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        timeFormatSpinner.setAdapter(adapter);

        final SwitchCompat displaySymbolSwitch = (SwitchCompat) view.findViewById(R.id.switchDisplaySymbol);
        final View[] defaultSymbolViews = new View[] {view.findViewById(R.id.textViewDefaultSymbol), view.findViewById(R.id.editTextDefaultSymbol)};
        displaySymbolSwitch.setOnCheckedChangeListener(new SwitchOnCheckedChangeListener(defaultSymbolViews));
        SwitchOnCheckedChangeListener.setEnabled(defaultSymbolViews, displaySymbolSwitch.isChecked());

        final SwitchCompat fadeSwitch = (SwitchCompat) view.findViewById(R.id.switchFade);
        final View[] fadeViews = new View[] {view.findViewById(R.id.textViewFadePoint), view.findViewById(R.id.editTextFadePoint)};
        fadeSwitch.setOnCheckedChangeListener(new SwitchOnCheckedChangeListener(fadeViews));
        SwitchOnCheckedChangeListener.setEnabled(fadeViews, fadeSwitch.isChecked());

        Tooltip.make(getActivity(),
                new Tooltip.Builder(101)
                        .anchor(view.findViewById(R.id.textViewMaxDays), Tooltip.Gravity.RIGHT)
                        .closePolicy(new Tooltip.ClosePolicy()
                                .insidePolicy(true, false)
                                .outsidePolicy(true, false), 0)
                        .activateDelay(900)
                        .showDelay(400)
                        .text("Android PopupWindow with Tooltip Arrow right side of button or view or layout")
                        .maxWidth(600)
                        .withArrow(true)
                        .withOverlay(false).build()
        ).show();

        return view;
    }
}
