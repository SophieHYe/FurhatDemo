package furhatos.app.leadershiptraining.nlu

import furhatos.nlu.*
import furhatos.nlu.grammar.Grammar
import furhatos.nlu.kotlin.grammar
import furhatos.nlu.common.Number
import furhatos.util.Language

class RequestOptions: Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("What options do you have?",
                "What trainings do you have?",
                "What are the topics?",
                "What do you have?")
    }
}


class WhatInstantFeedback: Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("what's give instant feedback?, how to give instant feedback?, what's instant feedback about?")
    }
}


class FrequentInFeedback: Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("I do it all the time.", "I do it from time to time.", "Happens on rare occasions.", "Never done it.")
    }
}


class BehaviorIdentifiedFeedback: Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("appreciate an open attitude", " appreciate good team behavior.")
    }
}

class WhyInstantFeedback: Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("why we need it?, why we want to give instant feedback instead of normal one ?")
    }
}



class FeelingFeedback: Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("It's amazing!", "It's helpful!", "It's encouraging!")
    }
}



class QuestionFeedback: Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("who to give it to?", "who should we give the feedback to?")
    }
}

class PracticeFeedback: Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("I will definitely do it! ")
    }
}


class TopicList : ListEntity<ChosenTopic>()
class SubTopicList : ListEntity<ChosenSubTopic>()

class ChosenTopic(
        var topic : Topic? = null) : ComplexEnumEntity() {
    override fun getEnum(lang: Language): List<String> {
        return listOf("@topic")
    }

    override fun toText(): String {
        return generate("$topic ")
    }
}


class ChosenSubTopic(
        var subtopic : SubTopic? = null) : ComplexEnumEntity() {
    override fun getEnum(lang: Language): List<String> {
        return listOf("@subtopic")
    }

    override fun toText(): String {
        return generate("$subtopic ")
    }
}

class Topic : EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang: Language): List<String> {
        return listOf("communication", "team", "feedback", "performance","time management", "self-leadership")
    }
}


class SubTopic : EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang: Language): List<String> {
        return listOf("send a message with positive feedback", "give instant feedback", "give corrective feedback", " and  provide feedback linked to core values")
    }
}


class ChooseTopic(var topic : TopicList? = null) : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("@topic","let's try @topic","@topic sounds interesting", "I want to practice @topic", "I would like to practice @topic", "I want to study @topic")
    }
}


class ChooseSubTopic(var subtopic : SubTopicList? = null) : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("@subtopic","let's try @subtopic","@subtopic sounds interesting", "I want to practice @subtopic", "I would like to practice @subtopic", "I want to study @subtopic")
    }
}

//class TopicSkills(){
//    fun returnTopicSkills(var topic: Topic? = null): String {
//        if(topic=="feedback"){
//            return "improve your skills for providing positive feedback to the team."
//        }else if(topic=="communication"){
//            return "improve your skills for providing positive feedback to the team."
//        }else if(topic=="team"){
//            return "improve your skills for providing positive feedback to the team."
//        }else if(topic=="performance"){
//            return "improve your skills for providing positive feedback to the team."
//        }else {
//            return "improve your skills for providing positive feedback to the team."
//        }
//    }
//}