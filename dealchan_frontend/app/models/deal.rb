class Deal < ActiveRecord::Base

	belongs_to :category
	belongs_to :city

	DEALS_PER_PAGE = 5
	DEALS_PER_HIGHLIGHT = 5

	def self.get_five_deals_of(args={})

		data = []

		if args[:min_price] && args[:max_price] 
			data = self.where("currentPrice >= :min_price AND currentPrice < :max_price", {
				:min_price => args[:min_price],
				:max_price => args[:max_price]
			})
		elsif args[:category]
			data = self.where(:category_id => args[:category])
		end
		data.where( :city_id => args[:city])
		.limit(DEALS_PER_HIGHLIGHT)

	end

	def self.get_deal_with_price_range_and_city(min_price, max_price, city, page_number)
		where("currentPrice >= :min_price AND currentPrice < :max_price", {
			:min_price => min_price,
			:max_price => max_price
		})
		.where( :city_id => city)
		.order("currentPrice ASC")
		.group("title")
		.limit(DEALS_PER_PAGE)
		.offset(DEALS_PER_PAGE * (page_number - 1))
	end


	def self.get_deal_with_category_and_city(category, city, page_number)
		where(:category_id => category, 
			:city_id => city)
		.order("discount DESC")
		.group("title")
		.limit(DEALS_PER_PAGE)
		.offset(DEALS_PER_PAGE * (page_number-1))
	end

	def self.get_deal_with_id(id)

		where( :id => id )

	end

end
