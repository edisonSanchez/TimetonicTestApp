package com.edisonsanchez.timetonictestapp.data

import com.google.gson.annotations.SerializedName
import java.io.Serial

data class ResponseOkCreateAppKey (
    var status: String,
    var appkey: String,
    var id: String,
    var createdVNB: String,
    var req: String
)

data class ResponseOkLogin (
    var status: String,
    var oauthkey: String,
    var id: String,
    @SerializedName("o_u")
    var oauthUser: String,
    var createdVNB: String,
    var req: String
)

data class ResponseOkCreateSessionKey (
    var status: String,
    var sesskey: String,
    var id: String,
    var restrictions: Restrictions,
    var appName: String,
    var createdVNB: String,
    var req: String
)

data class Restrictions (
    @SerializedName("carnet_code")
    var carnetCode : String?,
    @SerializedName("carnet_owner")
    var carnetOwner : String?,
    var readonly: Boolean,
    var hideTables: Boolean,
    var hideMessages: Boolean,
    var internal: Boolean
)

data class ResponseGetAllBooks (
    var status: String,
    var sstamp: Long,
    var allBooks: Books,
    var createdVNB: String,
    var req: String
)

data class Books (
    var nbBooks: Int,
    var nbContacts: Int,
    var contacts: List<Contact>,
    var books: List<Book>
)

data class Contact (
    @SerializedName("u_c")
    var oauthKey: String,
    var lastName : String,
    var firstName: String,
    var sstamp: Long,
    var isConfirmed: Boolean
)

data class Book (
    var invited: Boolean,
    var accepted: Boolean,
    var archived: Boolean,
    var notificationWeb: String,
    var notificationAudio: String,
    var showFpOnOpen: Boolean,
    var sstamp: Long,
    var del: Boolean,
    var hideMessage: String,
    var hideBookMembers: String,
    var description: String?,
    var defaultTemplate: String,
    var isDownloadable: Boolean,
    var canDisableSync: Boolean,
    @SerializedName("b_c")
    var bookCode: String,
    @SerializedName("b_o")
    var bookOwner: String,
    var cluster: String,
    var tags: String?,
    var langs: String?,
    @SerializedName("contact_u_c")
    var contact: String?,
    var nbNotRead: Int,
    var nbMembers: Int,
    var members : List<Member>,
    var fpForm: Form,
    var lastMsg: LastMessage,
    var nbMsgs: Int,
    var userPrefs: UserPrefs,
    var ownerPrefs: OwnerPrefs,
    var sbid: Long,
    var lastMsgRead: Long,
    var lastMedia: Int,
    var favorite: Boolean,
    var order: Int
)

data class Member (
    @SerializedName("u_c")
    var userCode: String,
    var invite: String,
    var right: Int,
    var access: Int,
    var hideMessage: String,
    var hideBookMembers: String,
    var apiRight: String,
    var fpForm: Form,
    var lastMsg: LastMessage,
    var nbMsgs: Int,
    var userPrefs: UserPrefs
)

data class Form (
    var fpid: Int,
    var name: String,
    var lastModified: Long
)

data class LastMessage (
    var smid: Long,
    var uuid: String,
    var sstamp: Long,
    var lastCommentId: Long,
    var msgBody: String,
    var msgMethod: String,
    var msgColor: String,
    var nbComments: Int,
    var pid: Int,
    var nbMedias: Int,
    var nbEmailCids: Int,
    val nbDocs: Int,
    @SerializedName("b_c")
    val bookCode: String,
    @SerializedName("b_o")
    val bookOwner: String,
    @SerializedName("u_c")
    var userCode: String,
    var linkedRowId: String?,
    var linkedTabId: String?,
    var linkMessage: String,
    var linkedFieldId: String?,
    var msg: String,
    var del: Boolean,
    var created: Long,
    var lastModified: Long,
    var docs: List<Doc>,
)

data class Doc (
    var id: Long,
    var ext: String,
    var originName: String,
    var interName: String,
    var uuid: String,
    var size: Int,
    var type: String,
    var del: Boolean
)

data class UserPrefs (
    var maxMsgsOffline: Int,
    var syncWithHubic: Boolean,
    var notificationEnabled: String,
    var uCoverLetOwnerDecide: Boolean,
    var uCoverColor: String,
    var uCoverUseLastImg: Boolean,
    var uCoverImg: String?,
    var uCoverType: String,
    var inGlobalSearch: Boolean,
    var inGlobalTasks: Boolean,
    var notifyEmailCopy: Boolean,
    var notifySmsCopy: Boolean,
    var notifyMobile: Boolean,
    var notifyWhenMsgInArchivedbook: Boolean
)

data class OwnerPrefs (
    var fpAutoExport: Boolean,
    var oCoverColor: String,
    var oCoverUseLastImg: Boolean,
    var oCoverImg: String?,
    var oCoverType: String,
    var authorizeMemberBroadcast: Boolean,
    var acceptExternalMsg: Boolean,
    var title: String,
    var notifyMobileConfidential: Boolean
)