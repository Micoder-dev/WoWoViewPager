package com.nightonke.wowoviewpagerexample.svg;

import android.os.Bundle;

import com.nightonke.wowoviewpager.Animation.WoWoInterfaceAnimation;
import com.nightonke.wowoviewpager.Animation.WoWoTranslationAnimation;
import com.nightonke.wowoviewpagerexample.R;
import com.nightonke.wowoviewpagerexample.WoWoActivity;

public class SVGActivity extends WoWoActivity {

    @Override
    protected int contentViewRes() {
        return R.layout.activity_svg;
    }

    @Override
    protected int fragmentNumber() {
        return 6;
    }

    protected Integer[] fragmentColorsRes() {
        return new Integer[]{
                R.color.blue_1,
                R.color.blue_2,
                R.color.blue_3,
                R.color.blue_4,
                R.color.blue_5,
                R.color.blue_5,
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AnimatedSvgView svgView0 = (AnimatedSvgView) findViewById(R.id.svg_0);
        AnimatedSvgView svgView1 = (AnimatedSvgView) findViewById(R.id.svg_1);
        AnimatedSvgView svgView2 = (AnimatedSvgView) findViewById(R.id.svg_2);
        AnimatedSvgView svgView3 = (AnimatedSvgView) findViewById(R.id.svg_3);
        AnimatedSvgView svgView4 = (AnimatedSvgView) findViewById(R.id.svg_4);

        setSvg(svgView0, SVG.GOOGLE);
        setSvg(svgView1, SVG.GITHUB);
        setSvg(svgView2, SVG.TWITTER);
        setSvg(svgView3, SVG.JRUMMY_APPS);
        setSvg(svgView4, SVG.BUSYBOX_LOGO);

        wowo.addAnimation(svgView0)
                .add(WoWoInterfaceAnimation.builder().page(0).implementedBy(svgView0).build())
                .add(WoWoTranslationAnimation.builder().page(1).fromX(0).toX(-screenW).keepY(0).build());
        wowo.addAnimation(svgView1)
                .add(WoWoInterfaceAnimation.builder().page(1).implementedBy(svgView1).build())
                .add(WoWoTranslationAnimation.builder().page(2).fromX(0).toX(-screenW).keepY(0).build());
        wowo.addAnimation(svgView2)
                .add(WoWoInterfaceAnimation.builder().page(2).implementedBy(svgView2).build())
                .add(WoWoTranslationAnimation.builder().page(3).fromX(0).toX(-screenW).keepY(0).build());
        wowo.addAnimation(svgView3)
                .add(WoWoInterfaceAnimation.builder().page(3).implementedBy(svgView3).build())
                .add(WoWoTranslationAnimation.builder().page(4).fromX(0).toX(-screenW).keepY(0).build());
        wowo.addAnimation(svgView4)
                .add(WoWoInterfaceAnimation.builder().page(4).implementedBy(svgView4).build());
    }

    private void setSvg(AnimatedSvgView svgView, SVG svg) {
        svgView.setGlyphStrings(svg.glyphs);
        svgView.setFillColors(svg.colors);
        svgView.setViewportSize(svg.width, svg.height);
        svgView.setTraceResidueColor(0x32000000);
        svgView.setTraceColors(svg.colors);
        svgView.setTraceTimePerGlyph(5000);
        svgView.setTraceTime(5000);
        svgView.setFillTime(5000);
        svgView.rebuildGlyphData();
    }
}
