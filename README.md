# MI_HF_2022_Connect4
## Feladat
Ebben a házi feladatban a feladat egy ágens implementálása, amely képes egy másik ágenst legyőzni a
Connect4 nevű játékban. A játék kétszemélyes, egy 6×7-es táblán játszódik. A játékosok felváltva ejtik
bele a táblába a saját színüket. A győzelemhez 4 saját színt kell egy vonalban kirakni (függőlegesen/
vízszintesen/átlósan). A játék, egy már megoldott játék, tehát annak jelenlegi állapotát ismerve a kimenete
ismert, feltéve, hogy a játékosok tökéletesen játszanak. A tökéletes játékhoz azonban vagy előzetes
számítások, vagy lépésenkét sok idő szükséges. Ezért a MiniMax algoritmus mélységkorlátozott változatának
használatát javasoljuk. A feladat maximális pontot érő megoldásához szükség lehet bizonyos extrák
használatára, ilyenek többek között az α-β nyesés és a transzpozíciós tábla.