rootProject.name = "book-management-hexagonal-kotlin"

include("bootstrap")
include("adapter:inbound:api")
include("adapter:outbound:persistence")
include("application")
include("domain")
