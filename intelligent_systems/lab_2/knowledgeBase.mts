import { Predicate, Rule, Substitution } from "./queryLang.mjs";

export class KnowledgeBase {
  facts = new Set<Predicate>();
  rules = new Set<Rule>();
  constructor(private readonly inferenceEngine: InferenceEngine) {}
  query(q: string) {
    return this.inferenceEngine
      .performQuery(this.facts, new Predicate(q))
      .values();
  }
  addFact(f: string) {
    for (const fact of this.facts) {
      if (fact.t === f) {
        return;
      }
    }
    const fact = new Predicate(f);
    this.facts.add(fact);
  }
  addRule(t: string) {
    for (const rule of this.rules) {
      if (rule.text === t) {
        return;
      }
    }
    console.log(t);
    const rule = new Rule(t);
    this.rules = this.rules.add(rule);
    this.facts.forEach((fact) => {
      // 1 match rule and return variables
      // 2 map predicate to fact and save it
    });
  }
}

export interface InferenceEngine {
  performQuery(facts: Set<Predicate>, q: Predicate): Set<Substitution>;
}
