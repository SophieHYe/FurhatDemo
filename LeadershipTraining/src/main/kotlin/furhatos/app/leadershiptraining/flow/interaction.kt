package furhatos.app.leadershiptraining.flow


import furhatos.nlu.common.*
import furhatos.flow.kotlin.*
import furhatos.app.leadershiptraining.nlu.*

val Start : State = state(Interaction) {

    onEntry {
        random(
                { furhat.ask("Hi there. I am furhat, your leadership training assistant, ready to practice? ") },
                { furhat.ask("how are you, ready to practice today?") }
        )
    }

    onResponse<No> {
        furhat.say("Okay, have a nice day!")
        goto(Idle)
    }

    onResponse<Yes> {
        goto(TrainingSuggestions)
    }
}


val Options = state(Interaction) {
    onResponse<ChooseTopic> {
        val trainingTopic = it.intent.topic
        if (trainingTopic != null) {
            goto(TopicReceived(trainingTopic))
        } else {
            propagate()
        }
    }


    onResponse<ChooseSubTopic> {
        val subTopic = it.intent.subtopic
        if (subTopic != null) {
            goto(SubTopicReceived(subTopic))
        } else {
            propagate()
        }
    }


    onResponse<WhatInstantFeedback> {
        furhat.ask("Instant feedback means you observe a good behavior and instantly praise it,The trick is to do it instantly, because that’s when it has the biggest impact on the receiver," +
                "If you can learn how to observe small things and praise the behavior directly, you show presence and that you see your colleagues")
    }


    onResponse<WhyInstantFeedback> {
        furhat.say("Because giving instant feedback will achieve biggest impact when given, you show presence and you enhance positive behaviors")
        goto(PraticeTraining())

    }


    onResponse<FrequentInFeedback> {
        furhat.say("No worries, always catch your team members when they do something right!")
        furhat.say("Here's an example: Hey Pete, I noticed that you were really open to Anna’s idea at the meeting. It made the dialogue very constructive. ")
        furhat.ask("Can you identify the good behaviors to give feedback on?")
    }

    onResponse<BehaviorIdentifiedFeedback> {
        furhat.say("Yes, in this example, the feedback shows appreciation for an initiative; Give cred to pro-activity in a meeting," +
                "Reward good team behavior; Praise an open attitude and comment on a good presentation! ")
        furhat.ask("what do you think?")
    }


    onResponse<FeelingFeedback> {
        furhat.ask("Yes, it's amazing! The advantage of diung it publicly is that your spread positive energy and give the person public recognition." +
                "If your feedback is on negative matters, never do it publicly. ")
    }

    onResponse<QuestionFeedback> {
        furhat.say("Emm, this exercise is not about who,it's about observing a positive behavior and instantly praising it. So dont think" +
                "about who, think about what you see.  ")
        furhat.ask("Would you like to practice giving instant feedback to a colleague?")
    }

    onResponse<PracticeFeedback> {
        furhat.ask("Cool, I am very look forward to your sharing your experience next time!")
    }


    onResponse<Thanks> {
        furhat.say("My pleasure!")
    }


    onResponse<RequestOptions> {
        furhat.say("I can provide help on six topics, they are  ${Topic().optionsToText()}")
        furhat.ask("Are you interested in any of them?")
    }

}


    val TrainingSuggestions = state(Options) {
        onEntry {
            furhat.ask("which topic do you want to practice?")
        }
    }

    fun TopicReceived(topic: TopicList): State = state(furhatos.app.leadershiptraining.flow.Options) {
        onEntry {
            furhat.say("${topic.text}, what a lovely choice! Have you practice this theme before?")
            furhat.ask("Do you know what is ${topic.text} ?")
        }

        onResponse<No> {
            furhat.say("Okay, in the training of ${topic.text},  you will be learning how to improve your skill in providing positive feedback. ")
            furhat.ask("In the theme of ${topic.text}, we have different subtopics, such as ${SubTopic().optionsToText()}, any one you want to practice?")

        }

        onResponse<Yes> {
            furhat.say("Wonderful, in the training of ${topic.text},  let's see if any sub topics you haven't practice yet. ")
            furhat.ask("In the theme of ${topic.text}, we have different subtopics, such as ${SubTopic().optionsToText()}, any one you want to practice?")

        }

    }


    fun PraticeTraining(): State = state(furhatos.app.leadershiptraining.flow.Options) {
        onEntry {
            furhat.ask("Ready to practice?")
        }

        onResponse<Yes> {
            furhat.ask("Great, let me ask a question, how often do you notice positive behaviors in your colleagues and praising them instantly?" +
                    "do it all the time? from time to time? happens on rare occasions? or you never done it?")

        }

        onResponse<No> {
            furhat.say("Ok, see you soon!")
            goto(Idle)

        }


    }


fun SubTopicReceived(subtopic: SubTopicList) : State = state(furhatos.app.leadershiptraining.flow.Options) {
    onEntry {
        furhat.ask(" ${subtopic.text} is an excellent way to enhance positive behaviors!")
    }
}
