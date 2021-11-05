package io.bushuev.emailvalidator.warning

import emailvalidator4j.parser.{Warnings => WarningsJ}

import scala.util.control.NoStackTrace

trait Warning extends Throwable with NoStackTrace {

  def fromJava(warnigsJ: WarningsJ): Warning = warnigsJ match {
    case WarningsJ.DEPRECATED_QP                         => Warning.DEPRECATED_QP
    case WarningsJ.CFWS_FWS                              => Warning.CFWS_FWS
    case WarningsJ.COMMENT                               => Warning.COMMENT
    case WarningsJ.DEPRECATED_CFWS_NEAR_AT               => Warning.DEPRECATED_CFWS_NEAR_AT
    case WarningsJ.RFC5321_QUOTEDSTRING                  => Warning.RFC5321_QUOTEDSTRING
    case WarningsJ.RFC5321_LOCALPART_TOO_LONG            => Warning.RFC5321_LOCALPART_TOO_LONG
    case WarningsJ.RFC5321_ADDRESS_LITERAL               => Warning.RFC5321_ADDRESS_LITERAL
    case WarningsJ.RFC5322_DOMAIN_LITERAL                => Warning.RFC5322_DOMAIN_LITERAL
    case WarningsJ.RFC5321_IPV6_DEPRECATED               => Warning.RFC5321_IPV6_DEPRECATED
    case WarningsJ.RFC5322_IPV6_MAX_GROUPS               => Warning.RFC5322_IPV6_MAX_GROUPS
    case WarningsJ.RFC5322_IPV6_DOUBLE_COLON             => Warning.RFC5322_IPV6_DOUBLE_COLON
    case WarningsJ.RFC5322_DOMAIN_LITERAL_OBSOLETE_DTEXT => Warning.RFC5322_DOMAIN_LITERAL_OBSOLETE_DTEXT
    case WarningsJ.RFC5322_IPV6_START_WITH_COLON         => Warning.RFC5322_IPV6_START_WITH_COLON
    case WarningsJ.RFC5322_IPV6_END_WITH_COLON           => Warning.RFC5322_IPV6_END_WITH_COLON
    case WarningsJ.RFC5322_IPV6_BAD_CHAR                 => Warning.RFC5322_IPV6_BAD_CHAR
    case WarningsJ.RFC5322_IPV6_GROUP_COUNT              => Warning.RFC5322_IPV6_GROUP_COUNT
    case WarningsJ.RFC5321_DOMAIN_TOO_LONG               => Warning.RFC5321_DOMAIN_TOO_LONG
    case WarningsJ.RFC1035_LABEL_TOO_LONG                => Warning.RFC1035_LABEL_TOO_LONG
    case WarningsJ.DEPRECATED_COMMENT                    => Warning.COMMENT
    case str                                             => throw new IllegalArgumentException(s"Unknown warning: ${str.getMessage}")
  }

}

object Warning {

  case object DEPRECATED_QP           extends Warning { override def getMessage = "Deprecated Quoted Part"       }
  case object CFWS_FWS                extends Warning { override def getMessage = "Folding White Space"          }
  case object COMMENT                 extends Warning { override def getMessage = "Email address with a comment" }
  case object DEPRECATED_CFWS_NEAR_AT extends Warning { override def getMessage = "CFWS near @"                  }
  case object RFC5321_QUOTEDSTRING    extends Warning { override def getMessage = "Quoted string"                }

  case object RFC5321_LOCALPART_TOO_LONG extends Warning {

    override def getMessage =
      "Local part exceeds max length of 64, otherwise valid from RFC5322 2.1.1"
  }
  case object RFC5321_ADDRESS_LITERAL   extends Warning { override def getMessage = "Found address literal"            }
  case object RFC5322_DOMAIN_LITERAL    extends Warning { override def getMessage = "Found domain literal"             }
  case object RFC5321_IPV6_DEPRECATED   extends Warning { override def getMessage = "Found deprecated address literal" }
  case object RFC5322_IPV6_MAX_GROUPS   extends Warning { override def getMessage = "Max groups reached"               }
  case object RFC5322_IPV6_DOUBLE_COLON extends Warning { override def getMessage = "Double :: in address literal"     }

  case object RFC5322_DOMAIN_LITERAL_OBSOLETE_DTEXT extends Warning {
    override def getMessage = "Obsolete DTEXT in domain literal"
  }

  case object RFC5322_IPV6_START_WITH_COLON extends Warning {
    override def getMessage = "Additional colon after IPv6 tag"
  }
  case object RFC5322_IPV6_END_WITH_COLON   extends Warning { override def getMessage = "Colon found at the end"            }
  case object RFC5322_IPV6_BAD_CHAR         extends Warning { override def getMessage = "Literal contains an invlaid char"  }
  case object RFC5322_IPV6_GROUP_COUNT      extends Warning { override def getMessage = "Unexpected number of groups"       }
  case object RFC5321_DOMAIN_TOO_LONG       extends Warning { override def getMessage = "Domain exceeds 255 maximum length" }

  case object RFC1035_LABEL_TOO_LONG extends Warning {
    override def getMessage = "Domain label exceeds 63 maximum length"
  }
  case object DEPRECATED_COMMENT     extends Warning { override def getMessage = "Deprecated place for a comment" }

}
