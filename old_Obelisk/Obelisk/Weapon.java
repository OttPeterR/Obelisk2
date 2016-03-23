
public interface Weapon
{
String getName();
double getMaxAmmo();
double getCurrentAmmo();
double getMaxClipAmmo();
double getCurrentClipAmmo();
void addAmmo(Weapon w);
void fullAmmo();
boolean canShoot();
Bullet shoot();
void reload();
void combineAmmo(PickUpEntity e);
}
