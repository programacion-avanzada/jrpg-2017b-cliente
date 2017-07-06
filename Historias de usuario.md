#  Historias de usuario

## 01 Items:
Como `jugador` quiero que el `juego` posea una serie de `items`, y con estos aumentar las stats de los personajes, de manera tal que sea
notorio el cambio con los mismos a la hora de batallar, y poder ver la vida y la energía de los personajes con las stats que corresponden al tener los items equipados. Los items se obtendrán mediante una batalla, de haberla ganado, el personaje ganador obtendrá un item.
### Condiciones de aceptación:
1. El item debe modificar al menos un atributo de mi personaje.
2. El item debe tener una foto única que lo representa junto con su nombre.
3. Sólo debe dar un item al personaje ganador de la batalla.

## 02 Inventario:
Como `jugador` quiero que mi `personaje` posea un `inventario` en el cual pueda visualizar los `items` del `personaje` y a su vez, visualizar los `atributos` que estos items aumentan a las stats del personaje. Teniendo en cuenta que el inventario tiene un máximo de 9 items.
### Condiciones de aceptación:
1. Debo poder visualizar correctamente los items de mi personaje.
2. Mi inventario debe llenarse cuando éste posea 9 items.
3. Al ganar una batalla con el inventario lleno, esta no debe darme ningún item.

## 03 Manipulación del inventario:
Como jugador, quiero tener la habilidad de `eliminar` items del inventario del personaje de manera tal de dejar libre el espacio ocupado por éste item.
### Condiciones de aceptación:
1. Debo notar de inmediato el cambio de atributos de mi personaje al eliminar el item.
2. Una vez eliminatdo, si salgo y vuelvo a entrar al juego, el item debe de no aparecer en mi inventario.

## 04 Mercado:
Como `jugador` quiero que mi `personaje` posea la habilidad de intercambiar sus items con los de otro `personaje`, y que realizado el `intercambio` se aprecien las nuevas `stats` de mi `personaje`. Nótese que existe un área de `comercio`, solo se puede comerciar si solo si están dentro de dicha zona.
### Condiciones de aceptación:
1. Sólo se debe permitir el intercambio de items es decir no puede existir un intercambio entre un item y no recibir ninguno.
2. Se debe controlar que la suma total de los items finales de cada `personaje` no supere el límite máximo, de hacerlo, no se debe permitir el intercambio.
3. El intercambio debe ser realizado sí sólo si ambos `personajes` aceptan el intercambio.
4. Cuando selecciono un `item`, debe aparecer las `stats` de este, para que el `jugador` sepa que `stats` posee ese `item`.
5. Si un `jugador` pretende comerciar con otro y este ultimo está fuera de la zona de comercio, se debe mostrar el mensaje correspondiente.
6. Si un `jugador` pretende comerciar con otro y éste está fuera de la zona de comercio, se debe msotrar el mensaje correspondiente.

## 05 Chat:
Como `jugador` quiero poseer la habilidad de comunicarme con otros `jugadores` mediante un `Chat`, así como también quiero una `Sala` en la cual el mensaje enviado sea recibido por todos los `jugadores`.
### Condiciones de aceptación:
1. Al enviar un mensaje el `jugador` receptor debe recibirlo instantáneamente.
2. Al enviar un mensaje en la `Sala` todos los jugadores deben recibir el mensaje instantáneamente.
3. Se debe poder batallar y hablar al mismo tiempo.
4. Se debe poder comerciar y hablar al mismo tiempo.
