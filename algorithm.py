class Rule():

    def __init__(self, antecedent, consequent):
        self.antecedent = antecedent
        self.consequent = consequent

    def getAntecedent(self):
        return self.antecedent

    def getConsequent(self):
        return self.consequent

    def printRule(self):
        print(self.getAntecedent() + " => " + self.getConsequent())

class ForwardChaining():
    rules = [
        Rule("OS = iOS", "brand Apple"),  # R1
        Rule("OS = Android", "brand Samsung OR brand Xiaomi OR brand Oppo OR brand Vivo OR brand Realme"),  # R2
        Rule("memory <= 64 gb", "small memory"),  # R3
        Rule("memory = 128 gb", "mid memory"),  # R4
        Rule("memory >= 256 gb", "big memory"),  # R5
        Rule("ram < 6 gb AND brand NOT Apple", "small ram"),  # R6
        Rule("ram = 6 gb AND brand NOT Apple", "mid ram"),  # R7
        Rule("ram >= 8 gb AND brand NOT Apple", "big ram"),  # R8
        Rule("ram = 4 gb AND brand = Apple", "Apple mid ram"),  # R9
        Rule("ram >= 6 gb AND brand = Apple", "Apple big ram"),  # R10
        Rule("price < 3.000.000", "low price"),  # R11
        Rule("price >= 3.000.000 AND price <= 6.000.000", "mid price"),  # R12
        Rule("price > 6.000.000", "high price"),  # R13
        Rule("user preference = need wide camera OR user preference = need ultrawide camera OR user preference = need macro camera OR user preference = need night vision OR user preference = need 4K camera OR user preference = need 8k camera", "good camera"),  # R17
        Rule("user preference = need high refresh rate OR user preference = need OLED screen OR user preference = need retina display", "good screen"),  # R18
        Rule("user preference = need big battery OR user preference = need fast charging >= 33W",
            "good battery"),  # R19
        Rule("small ram AND small memory", "low spec"),  # R14
        Rule("mid ram AND mid memory", "mid spec"),  # R15
        Rule("big ram AND big memory", "high spec"),  # R16
        Rule("brand = Apple AND low price OR mid price",
            "nothing to recommend"),  # R20
        Rule("brand NOT Apple AND (small memory AND mid ram OR big ram) OR (big memory AND small ram OR mid ram)",
            "nothing to recommend"),  # R21
        Rule("brand = Apple AND small memory OR Apple small ram",
            "iPhone SE 2022"),  # R23
        Rule("Apple big ram AND mid memory AND good camera AND good screen",
            "iPhone 14 Pro"),  # R24
        Rule("Apple big ram AND big memory AND good camera AND good screen AND dynamic island",
            "iPhone 14 Pro Max"),  # R25
        Rule("Apple big ram AND mid memory AND good screen AND screen size = 6.1”",
            "iPhone 14"),  # R26
        Rule("Apple big ram AND mid memory AND good screen AND screen size = 6.7”",
            "iPhone 14 Plus"),  # R27       # Rule 28
        Rule("brand = Samsung AND low spec AND low price", "Samsung A04"),      # Rule 29
        Rule("brand = Samsung AND low spec AND low price AND good camera", "Samsung A04s"),
        # Rule 30
        Rule("brand = Samsung AND mid spec AND mid price", "Samsung A23 5G"),
        # Rule 31
        Rule("brand = Samsung AND high spec AND mid price", "Samsung A33 5G"),
        # Rule 32
        Rule("brand = Samsung AND high spec AND high price AND foldable AND support pen", "Samsung Z Fold 4"),
        # Rule 33
        Rule("brand = Samsung AND high spec AND high price AND foldable AND simple", "Samsung Z Flip 4"),
        # Rule 34
        Rule("brand = Vivo AND low spec AND low price AND good battery", "Vivo Y16"),
        # Rule 35
        Rule("brand = Vivo AND low spec AND low price AND splash waterproof", "Vivo Y22"),
        # Rule 36
        Rule("brand = VIvo AND mid spec AND mid price", "nothing"),
        # Rule 37
        Rule("brand = Vivo AND high spec AND mid price AND good camera", "Vivo V25"),
        # Rule 38
        Rule("brand = Vivo AND high spec AND mid price AND good battery", "Vivo V25e"),
        # Rule 39
        Rule("brand = Vivo AND high spec AND high price AND good battery AND good camera", "Vivo V25 Pro"),
        # Rule 40
        Rule("brand = Realme AND low spec AND low price AND hood battery", "Realme C30s"),
        # Rule 41
        Rule("brand = Realme AND low spec AND low price AND good camera", "Realme C33"),
        # Rule 42
        Rule("brand = Realme AND mid spec AND mid price", "nothing to recommend"),
        # Rule 43
        Rule("brand = Realme AND high spec AND mid price", "Realme 10"),
        # Rule 44
        Rule("brand = Realme AND high spec AND high price", "nothing to recommend"),
        # Rule 45
        Rule("brand = Xiaomi AND high spec AND high price AND good screen AND good camera", "Xiaomi 12"),
        # Rule 46
        Rule("brand = Xiaomi AND high spec AND high price AND good screen AND good camera AND good battery", "Xiaomi 12 Pro"),
        # Rule 47
        Rule("brand = Xiaomi AND high spec AND high price", "Xiaomi 12 Lite"),
        # Rule 48
        Rule("brand = Xiaomi AND low spec AND low price", "Xiaomi Poco M5"),
        # Rule 49
        Rule("brand = Xiaomi AND low spec AND low price AND good battery AND good screen", "Xiaomi Redmi 10"),
        # R50
        Rule("brand = Xiaomi AND mid spec AND high price OR mid price OR low price", "nothing to recommend"),
        # R51
        Rule("brand = Xiaomi AND low spec AND high price OR mid price", "nothing to recommend"),
        # R52
        Rule("brand = Oppo AND high spec AND high price AND good camera AND good battery", "Oppo Reno 8 Pro"),
        # R53
        Rule("brand = Oppo AND high spec AND low price AND leather feel", "Oppo A77S"),
        # R54
        Rule("brand = Oppo AND high spec AND high price AND good camera", "Oppo Reno 8"),
        # R55
        Rule("brand = Oppo AND low spec AND low price AND good battery", "Oppo A17"),
        # R56
        Rule("brand = Oppo and low spec AND low price AND water resistant", "Oppo A17")
    ]
    
    def __init__(self, inp_data):
        self.data = inp_data

    def get_known_facts(self, data):
        facts = []
        brand = ""
        # Append OS fact
        if data['os'].lower() == 'ios':
            brand = "Apple"
            facts.append("brand Apple")
        else:
            brand = "Android"
            facts.append("brand Samsung OR brand Xiaomi OR brand Oppo OR brand Vivo OR brand Realme")
            
        # Append Memory fact
        if data['memory'] <= 64:
            facts.append("small memory")
        elif data['memory'] == 128:
            facts.append("mid memory")
        else:
            facts.append("big memory")
            
        # Append RAM fact
        if data['ram'] < 6 and brand != "Apple":
            facts.append("small ram")
        elif data['ram'] == 6 and brand != "Apple":
            facts.append("mid ram")
        elif data['ram'] >= 8 and brand != "Apple":
            facts.append("big ram")
        elif data['ram'] == 4 and brand == "Apple":
            facts.append("Apple mid ram")
        elif data['ram'] >= 6 and brand == "Apple":
            facts.append("Apple big ram")
        
        # Append price fact
        if data['price'] < 3000000:
            facts.append("low price")
        elif data['price'] >= 3000000 and data['price'] <= 6000000:
            facts.append("mid price")
        else:
            facts.append("high price")
            
        # Append user preference
        if 'camera' in data['user_preference'] or 'night vision' in data['user_preference']:
            facts.append("good camera")
        elif 'refresh rate' in data['user_preference'] or 'screen' in data['user_preference'] or 'display' in data['user_preference']:
            facts.append("good screen")
        elif 'battery' in data['user_preference'] or 'fast charging' in data['user_preference']:
            facts.append("good battery")
            
        return facts

    def solve(self):
        pass
    
    
if __name__ == '__main__':
    os = input("OS: ")
    memory = int(input("Memory: "))
    ram = int(input("RAM: "))
    price = int(input("Price: "))
    user_preference = input("User preference: ")
    
    input_data = {'os' : os, 'memory' : memory, 'ram' : ram, 'price' : price, 'user_preference' : user_preference}
    algo = ForwardChaining(input_data)
    