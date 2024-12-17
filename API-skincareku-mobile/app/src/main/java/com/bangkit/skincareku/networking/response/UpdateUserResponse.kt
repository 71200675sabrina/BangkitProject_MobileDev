package com.bangkit.skincareku.networking.response

import com.google.gson.annotations.SerializedName

data class UpdateUserResponse(

	@field:SerializedName("data")
	val data: UpdateUserData,

	@field:SerializedName("status")
	val status: String? = null
)

data class UpdateUser(

	@field:SerializedName("uid")
	val uid: String? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("skinProblem")
	val skinProblem: String? = null,

	@field:SerializedName("allergy")
	val allergy: String? = null,

	@field:SerializedName("birthDate")
	val birthDate: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("token")
	val token: String? = null
)

data class UpdateUserData(

	@field:SerializedName("user")
	val user: UpdateUser
)
