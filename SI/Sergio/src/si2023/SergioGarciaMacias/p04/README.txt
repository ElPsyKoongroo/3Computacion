

Cositas de la practica:

    Todo es exactamente igual que la practica anterior:

        Me referire como Parguelas a los Civiles.

        1. 	El algoritmo tiene una media del 90% de Winrate con las reglas que tiene ahora mismo.
            Se podria conseguir un WinRate del 100% pero a cambio dejariamos de obtener la maxima
            puntuancion (4049). El planteamiento actual es intentar rescatar a todos los Parguelas
            para obtener 1 punto por cada uno, y despues capturar a los malos.
            
            Se podria cambiar el planteamiento para no dejara caer a los parguela y mientras no haya
            ninguno cayendo que vaya a capturar enemigos. Asi obtendriamos el 100% de winrate.
            (Mirar HayEnemigo.java)
            
        2. 	He intentado comentar las cosas mas "chungas" de la practica para que no haga falta
            hacer un seguimiento del algoritmo para entender lo que hace. Tambien he intentado
            reducir la cantidad de magic numbers lo maximo posible.
            
        3.	No tomar en serio los nombres de las variables. (Probablemente el punto mas importante)


    Además:

        Para implementar las transiciones de los estados lo hago con un HashMap<String, Estado> para
        que haya un bucle de creacion de estados. Cada estado tiene asociada una String ID que es
        su identificador. Las transiciones usan esa ID para saber cual es su estado objetivo. 

        El WinRate del algoritmo es el mismo ya que solo cambia la forma en la que se ejecutan
        las acciones y se evaluan las condiciones.
