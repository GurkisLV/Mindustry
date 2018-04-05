package io.anuke.mindustry.content.bullets;

import com.badlogic.gdx.graphics.Color;
import io.anuke.mindustry.content.fx.BulletFx;
import io.anuke.mindustry.content.fx.Fx;
import io.anuke.mindustry.entities.Bullet;
import io.anuke.mindustry.entities.BulletType;
import io.anuke.mindustry.graphics.Palette;
import io.anuke.ucore.graphics.Draw;
import io.anuke.ucore.util.Angles;
import io.anuke.ucore.util.Mathf;

public class TurretBullets {

    public static final BulletType

    basicIron = new BulletType(3f, 0) {
        @Override
        public void draw(Bullet b) {
            drawBullet(Palette.bulletYellow, Palette.bulletYellowBack,
                    "bullet", b.x, b.y, 9f, 5f + b.fract()*7f, b.angle() - 90);
        }
    },

    basicSteel = new BulletType(6f, 0) {
        {
            hiteffect = BulletFx.hitBulletBig;
            knockback = 0.5f;
        }

        @Override
        public void draw(Bullet b) {
            drawBullet(Palette.bulletYellow, Palette.bulletYellowBack,
                    "bullet", b.x, b.y, 11f, 9f + b.fract()*8f, b.angle() - 90);
        }
    },

    basicLeadFragShell = new BulletType(3f, 0) {
        {
            hiteffect = BulletFx.flakExplosion;
            knockback = 0.8f;
            lifetime = 90f;
            drag = 0.01f;
        }

        @Override
        public void draw(Bullet b) {
            drawBullet(Palette.bulletYellow, Palette.bulletYellowBack,
                    "shell", b.x, b.y, 9f, 9f, b.angle() - 90);
        }

        @Override
        public void hit(Bullet b, float x, float y) {
            super.hit(b, x, y);
            for(int i = 0; i < 9; i ++){
                float len = Mathf.random(1f, 7f);
                float a = Mathf.random(360f);
                Bullet bullet = new Bullet(TurretBullets.basicLeadFrag, b,
                        x + Angles.trnsx(a, len), y + Angles.trnsy(a, len), a);
                bullet.velocity.scl(Mathf.random(0.2f, 1f));
                bullet.add();
            }
        }

        @Override
        public void despawned(Bullet b) {
            hit(b);
        }
    },

    basicLeadFrag = new BulletType(3f, 0) {
        {
            drag = 0.1f;
            hiteffect = Fx.none;
            despawneffect = Fx.none;
            hitsize = 4;
        }

        @Override
        public void draw(Bullet b) {
            drawBullet(Palette.bulletYellow, Palette.bulletYellowBack,
                    "bullet", b.x, b.y, 10f, 1f + b.fract()*11f, b.angle() - 90);
        }
    },

    basicFlame = new BulletType(2f, 4) {
        {
            hitsize = 7f;
            lifetime = 30f;
            pierce = true;
            drag = 0.07f;
            hiteffect = BulletFx.hitFlameSmall;
            despawneffect = Fx.none;
        }

        @Override
        public void draw(Bullet b) {}
    };

    private static void drawBullet(Color first, Color second, String name, float x, float y, float w, float h, float rot){
        Draw.color(second);
        Draw.rect(name + "-back", x, y, w, h, rot);
        Draw.color(first);
        Draw.rect(name, x, y, w, h, rot);
        Draw.color();
    }
}