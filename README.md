# TwinkleFix

为"乌托邦探险之旅"整合包制作的修复模组. 皆在尝试修复破坏游戏体验的问题并添加模组与模组之间的等价合成表.
## ⚠️ 注意事项

 - 该项目本名为TwinkleFix而没有"Utopia"字样. 在设置工作区之前克隆此储存库时最好提前重命名文件夹.
 - 部分合成表尽可能遵循等价交换 但可能会降低某些不同模组的相同物品的寻找难度. 如故意为之请自主删除合成表.

## 🔧 补丁列表

 - [修复Icarus模组1.16版本尝试在server-side应用客户端的代码](https://github.com/CatMoe/TwinkleUtopiaFix/blob/main/src/main/java/net/miaomoe/twinklefix/mixin/IcarusServerFixMixin.java) (最新版结构已更改 不确定是否已修复)
 - [修复即使在创造模式的情况下也无法移除Trinkets插槽中具有绑定诅咒的物品](https://github.com/CatMoe/TwinkleUtopiaFix/blob/main/src/main/java/net/miaomoe/twinklefix/trinkets/OverwrittenDefaultTrinkets.java) (Backport from [Trinkets@#310](https://github.com/emilyploszaj/trinkets/pull/310))
 - [静默断开连接提示](https://github.com/CatMoe/TwinkleUtopiaFix/blob/main/src/main/java/net/miaomoe/twinklefix/mixin/ClientConnectionMixin.java)

## 📚 [额外的配方](https://github.com/CatMoe/TwinkleUtopiaFix/tree/main/src/main/resources/data)

 - [Additional Additions (额外扩展)](https://github.com/Additional-Mods/additionaladditions)
    - [鸡块](https://www.mcmod.cn/item/587237.html) (需要 [熟鸡肉丁 (农夫乐事)](https://www.mcmod.cn/item/382091.html) x2)
 - [Candlelight (烛火晚宴)](https://github.com/satisfyu/Candlelight)
    - [黄油](https://www.mcmod.cn/item/695992.html)  (转换自 [黄油 (Croptopia)](https://www.mcmod.cn/item/470538.html))
    - [番茄](https://www.mcmod.cn/item/793069.html) (转换自 [番茄 (Croptopia)](https://www.mcmod.cn/item/470417.html) 或 [番茄 (农夫乐事)](https://www.mcmod.cn/item/382033.html))
 - [Croptopia (作物盛景)](https://github.com/ExcessiveAmountsOfZombies/Croptopia)
    - [黄油](https://www.mcmod.cn/item/470538.html)  (转换自 [黄油 (Candlelight)](https://www.mcmod.cn/item/695992.html))
    - [番茄](https://www.mcmod.cn/item/470417.html) (转换自 [番茄 (Candlelight)](https://www.mcmod.cn/item/793069.html)) 或 [番茄 (农夫乐事)](https://www.mcmod.cn/item/382033.html))
    - [奶瓶](https://www.mcmod.cn/item/470692.html) x4 (转换自 [奶瓶 (农夫乐事)](https://www.mcmod.cn/item/382040.html))
    - [米](https://www.mcmod.cn/item/470408.html) (转换自 [稻米](https://www.mcmod.cn/item/382036.html))
    - [牛油果](https://www.mcmod.cn/item/470437.html) (转换自 [牛油果 (多元乐事)](https://www.mcmod.cn/item/552755.html))
    - [牛油果](https://www.mcmod.cn/item/470437.html) x9 (需要 [牛油果捆 (多元乐事)](https://www.mcmod.cn/item/552754.html))
    - [牛油果树苗](https://www.mcmod.cn/item/470526.html) (转换自 [牛油果树苗 (多元乐事)](https://www.mcmod.cn/item/618128.html))
 - [Farmer's delight (农夫乐事)](https://github.com/newhoryzon/farmers-delight-fabric)
    - [番茄](https://www.mcmod.cn/item/382033.html) (转换自 [番茄 (Croptopia)](https://www.mcmod.cn/item/470417.html) 或 [番茄 (Candlelight)](https://www.mcmod.cn/item/793069.html))
    - [奶瓶](https://www.mcmod.cn/item/382040.html) (转换自 [奶瓶 (Croptopia)](https://www.mcmod.cn/item/470692.html) x4)
    - [稻米](https://www.mcmod.cn/item/382036.html) (转换自 [米 (Croptopia)](https://www.mcmod.cn/item/470408.html))
    - [番茄种子](https://www.mcmod.cn/item/382038.html) (转换自 [番茄种子 (Croptopia)](https://www.mcmod.cn/item/470497.html) 或 [番茄种子 (Candlelight)](https://www.mcmod.cn/item/793068.html))
 - [Cultural Delights (多元乐事)](https://github.com/Nyancatpig/Cultural-Delights)
    - [牛油果](https://www.mcmod.cn/item/552755.html) (转换自 [牛油果 (Croptopia)](https://www.mcmod.cn/item/470437.html))
    - [牛油果树苗](https://www.mcmod.cn/item/618128.html) (转换自 [牛油果树苗 (Croptopia)](https://www.mcmod.cn/item/470526.html))
    - [牛油果捆](https://www.mcmod.cn/item/552754.html) (需要 [牛油果 (Croptopia)](https://www.mcmod.cn/item/470437.html) x9)