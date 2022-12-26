package com.example.flowershop

class DataSource{

    companion object{

        fun createDataSet(): ArrayList<Product>{
            val list = ArrayList<Product>()
            list.add(
                Product(
                    0,
                    "Букет з білих троянд",
                    550,
                    "https://i.pinimg.com/564x/c0/9b/2b/c09b2bf9fc9e35a946848fc2c5933ee5.jpg"
                )
            )
            list.add(
                Product(
                    1,
                    "Мікс квітів",
                    400,
                    "https://i.pinimg.com/564x/d2/0d/18/d20d185b1f644b90b77f62bace506840.jpg"
                )
            )

            list.add(
                Product(
                    2,
                    "Піони",
                    580,
                    "https://i.pinimg.com/564x/d0/0a/42/d00a420a27f8bd2676db55479267a8b2.jpg"
                )
            )
            list.add(
                Product(
                    3,
                    "Букет з ромашок",
                    350,
                    "https://i.pinimg.com/564x/f1/75/1b/f1751bc4731d771b1240e5abb7940672.jpg"
                )
            )
            list.add(
                Product(
                    4,
                    "ЛіліЇ",
                    420,
                    "https://i.pinimg.com/564x/60/53/22/6053229433ce9fa78ae7dab85728a526.jpg"
                )
            )
            return list
        }
    }
}