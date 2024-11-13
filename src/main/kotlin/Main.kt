import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import java.io.File

// From https://github.com/paug/AndroidMakersBackend
fun main() {
    val seData = sessionizeData()
    val ofData = OfData(
        sessions = seData.sessions.filter { !it.isServiceSession }.map { session ->
            OfSession(
                id = session.id,
                title = session.title,
                startTime = session.startsAt.toString(),
                endTime = session.endsAt.toString(),
                speakers = session.speakers,
                trackTitle = seData.rooms.firstOrNull { it.id == session.roomId }?.name ?: "",
            )
        }.associateBy { it.id },
        speakers = seData.speakers.map {
            OfSpeaker(
                id = it.id,
                name = it.fullName,
                socials = it.links.map {
                    OfSocial(name = it.title, link = it.url)
                },
                photoUrl = it.profilePicture
            )
        }.associateBy { it.id }
    )

    val result = json.encodeToString(ofData)

    File("openfeedback-data/devfest24.json").writeText(result)
}

@Serializable
class OfData(
    val sessions: Map<String, OfSession>,
    val speakers: Map<String, OfSpeaker>
)

@Serializable
class OfSession(
    val id: String,
    val title: String,
    val startTime: String,
    val endTime: String,
    val speakers: List<String>,
    val trackTitle: String
)

@Serializable
class OfSpeaker(
    val id: String,
    val name: String,
    val photoUrl: String? = null,
    val socials: List<OfSocial>
)


@Serializable
class OfSocial(
    val name: String,
    val link: String
)
