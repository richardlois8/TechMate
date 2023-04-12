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


    